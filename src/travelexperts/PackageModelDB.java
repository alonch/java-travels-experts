package travelexperts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
                 
			//conn.close();
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
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pkg;
	}

	@Override
	public ArrayList<Package> get() {
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
			//conn.close();
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
	public ArrayList<ProductSupplierLink> getProductsSuppliers(int packageId) {
		ArrayList<ProductSupplierLink> productsSuppliers = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select ps.ProductSupplierId, ps.ProductId, ps.SupplierId, p.ProdName, s.SupName from "
					+ "packages_products_suppliers pps, products_suppliers ps, products p, suppliers s "
					+ "where pps.PackageId="+packageId+" and "
					+ "pps.ProductSupplierId=ps.ProductSupplierId and "
					+ "ps.productid=p.productid and "
					+ "ps.supplierid=s.supplierid";
			rs = stmt.executeQuery(sql);
			productsSuppliers = new ArrayList<ProductSupplierLink>();
			while (rs.next())
			{
				ProductSupplierLink productSupplier= new ProductSupplierLink();
				productSupplier.setId(rs.getInt("ProductSupplierId"));
				productSupplier.setProductId(rs.getInt("ProductId"));
				productSupplier.setSupplierId(rs.getInt("SupplierId"));
				productSupplier.setProductName(rs.getString("ProdName"));
				productSupplier.setSupplierName(rs.getString("SupName"));
				
				productsSuppliers.add(productSupplier);
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsSuppliers;
	}

	@Override
	public ArrayList<Package> getByCustomerId(int customerId) {
		ArrayList<Package> pkgs = new ArrayList<Package>();
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from packages, bookings, customers where "
					+ "customers.CustomerId="+customerId+" and "
					+ " customers.CustomerId=bookings.CustomerId and "
					+ "bookings.PackageId=packages.PackageId";
			rs = stmt.executeQuery(sql);
			
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
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pkgs;
	}

	@Override
	public ArrayList<ProductSupplierLink> getProductsSuppliersNot(
			int packageId) {
ArrayList<ProductSupplierLink> productsSuppliers = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql1 = "select ProductSupplierId from packages_products_suppliers where PackageId="+packageId;
			String sql = "select * from products_suppliers, products, suppliers "
					+ "where ProductSupplierId not in ( "+sql1+" ) and "
					+ "products_suppliers.productid=products.productid and "
					+ "products_suppliers.supplierid=suppliers.supplierid";
					
			rs = stmt.executeQuery(sql);
			productsSuppliers = new ArrayList<ProductSupplierLink>();
			while (rs.next())
			{
				ProductSupplierLink productSupplier= new ProductSupplierLink();
				productSupplier.setId(rs.getInt("ProductSupplierId"));
				productSupplier.setProductId(rs.getInt("ProductId"));
				productSupplier.setSupplierId(rs.getInt("SupplierId"));
				productSupplier.setProductName(rs.getString("ProdName"));
				productSupplier.setSupplierName(rs.getString("SupName"));
				
				productsSuppliers.add(productSupplier);
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsSuppliers;
	}
}
