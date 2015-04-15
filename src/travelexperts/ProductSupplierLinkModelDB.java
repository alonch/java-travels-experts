package travelexperts;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProductSupplierLinkModelDB implements ProductSupplierLinkModel{

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
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

	@Override
	public ProductSupplierLink get(int id) {
		ProductSupplierLink psl = new ProductSupplierLink();
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select ps.ProductSupplierId, ps.ProductId, ps.SupplierId, p.ProdName, s.SupName "
					+ "from products_suppliers ps, products p, suppliers s"
					+ "where ps.ProductSupplierId="+id+" and "
					+ "ps.ProductId=p.ProductId and"
					+ "ps.SupplierId=s.SupplierId";
			rs = stmt.executeQuery(sql);
			System.out.println("heloo");
			if (rs.next())
			{
				psl.setId(rs.getInt("ProductSupplierId"));
				psl.setProductId(rs.getInt("ProductId"));
				psl.setSupplierId(rs.getInt("SupplierId"));
				psl.setProductName(rs.getString("ProdName"));
				System.out.println(rs.getString("ProdName"));
				psl.setSupplierName(rs.getString("SupName"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return psl;
	}

}
