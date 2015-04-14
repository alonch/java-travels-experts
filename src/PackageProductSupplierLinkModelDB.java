import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class PackageProductSupplierLinkModelDB implements PackageProductSupplierLinkModel{

	private Connection conn;
	private Statement stmt;
	@Override
	public boolean link(int packageId, int productSupplierId) {
		boolean linked=false;
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Insert Into packages_products_suppliers Values("
					+packageId+","+productSupplierId;

			if (stmt.executeUpdate(strSQL) == 0)
			{
			   System.out.println("no rows inserted");
			}
			else{
				linked=true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linked;
	}

	@Override
	public boolean unlink(int packageId, int productSupplierId) {
		boolean unlinked=false;
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Delete from packages_products_suppliers where PackageId="+packageId
					+" and ProductSupplierId="+productSupplierId;

			if (stmt.executeUpdate(strSQL) == 0)
			{
			   System.out.println("no rows inserted");
			}
			else{
				unlinked=true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unlinked;
	}
}
