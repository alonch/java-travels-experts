import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PackageModelDB implements PackageModel {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public void add(Package pkg) {
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Insert Into packages Values("
					+ null +",'"+pkg.getName()+"' ,'"+pkg.getStartDate()+"','"+pkg.getEndDate()+"',"
					+ "'"+pkg.getDescription()+"',"+pkg.getBasePrice()+","+pkg.getAgencyCommission()+","
					+ 0+")";

			if (stmt.executeUpdate(strSQL) == 0)
			{
			   System.out.println("no rows inserted");
			}
                 
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save(Package pkg) {
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update packages "
					+ "set PkgName = '"+pkg.getName()+"',"
					+ "PkgStartDate = '"+pkg.getStartDate()+"',"
					+ "PkgEndDate = '"+pkg.getEndDate()+"',"
					+ "PkgDesc = '"+pkg.getDescription()+"',"
					+ "PkgBasePrice = "+pkg.getBasePrice()+","
					+ "PkgAgencyCommission = "+pkg.getAgencyCommission()+","
					+ "deleted = "+pkg.isDeleted()
					+ " where PackageId = "+pkg.getId();
			PreparedStatement preparedStmt = conn.prepareStatement(query);      
			preparedStmt.executeUpdate();
	       
			conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}

	@Override
	public Package get(int id) {
		Package pkg =null;
		try 
		{	
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from packages where PackageId = " + id;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				pkg = new Package(); 
				pkg.setId(rs.getInt("PackageId"));
				pkg.setName(rs.getString("PkgName"));
				pkg.setStartDate(rs.getDate("PkgStartDate"));
				pkg.setEndDate(rs.getDate("PkgEndDate"));
				pkg.setDescription(rs.getString("PkgDesc"));
				pkg.setBasePrice(rs.getDouble("PkgBasePrice"));
				pkg.setAgencyCommission(rs.getDouble("PkgAgencyCommission"));
				pkg.setDeleted(rs.getBoolean("deleted"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pkg;
	}

	@Override
	public List<Package> get() {
		ArrayList<Package> pkgs = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from packages";
			rs = stmt.executeQuery(sql);
			pkgs = new ArrayList<Package>();
			while (rs.next())
			{
				Package pkg= new Package();
				pkg.setId(rs.getInt("PackageId"));
				pkg.setName(rs.getString("PkgName"));
				pkg.setStartDate(rs.getDate("PkgStartDate"));
				pkg.setEndDate(rs.getDate("PkgEndDate"));
				pkg.setDescription(rs.getString("PkgDesc"));
				pkg.setBasePrice(rs.getDouble("PkgBasePrice"));
				pkg.setAgencyCommission(rs.getDouble("PkgAgencyCommission"));
				pkg.setDeleted(rs.getBoolean("deleted"));
				
				pkgs.add(pkg);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pkgs;
	}

	@Override
	public void addProductSupplier(int packageId, int productSupplierId) {
		PackageProductSupplierLinkModelDB linkMe = new PackageProductSupplierLinkModelDB();
		if(linkMe.link(packageId, productSupplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}

	@Override
	public void removeProductSupplier(int packageId, int productSupplierId) {
		PackageProductSupplierLinkModelDB unLinkMe = new PackageProductSupplierLinkModelDB();
		if(unLinkMe.unlink(packageId, productSupplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}

	@Override
	public List<PackageProductSupplierLink> getProductsSuppliers(int packageId) {
		ArrayList<PackageProductSupplierLink> packageProductsSuppliers = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from package_product_supplier where PackageId="+packageId;
			rs = stmt.executeQuery(sql);
			packageProductsSuppliers = new ArrayList<PackageProductSupplierLink>();
			while (rs.next())
			{
				PackageProductSupplierLink packageProductSupplier= new PackageProductSupplierLink();
				packageProductSupplier.setPackageId(rs.getInt("PackageId"));
				packageProductSupplier.setProductSupplierId(rs.getInt("ProductSupplierId"));
				
				packageProductsSuppliers.add(packageProductSupplier);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return packageProductsSuppliers;
	}
}
