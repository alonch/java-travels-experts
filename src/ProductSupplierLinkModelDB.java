import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProductSupplierLinkModelDB implements ProductSupplierLinkModel{

	private Connection conn;
	private Statement stmt;
	//private ResultSet rs;
	
	@Override
	public boolean link(int productId, int supplierId) {
		boolean linked=false;
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Insert Into products_suppliers Values("
					+ null +","+productId+","+supplierId;

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
	public boolean unlink(int productId, int supplierId) {
		boolean unlinked=false;
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Delete from products_suppliers where ProductId="+productId
					+" and SupplierId="+supplierId;

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
