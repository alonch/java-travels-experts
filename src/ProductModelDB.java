import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Team 4 - Mehmet Demirci 

public class ProductModelDB implements ItemModel {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
		
	@Override
	public void add(Item product) {
		
		try
        {  
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();

            String strSQL = "Insert Into products (ProdName) Values('" + product.getName() + "')";

			if (stmt.executeUpdate(strSQL) == 0)
			{
			   System.out.println("no rows inserted");
			}
                        
            conn.close();  
        }
        catch (Exception excp)
        {
            excp.printStackTrace();
        }
	}
	
	@Override
	public void save(Item product) {
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update products set ProdName = ? where ProductId = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, product.getName());	      
			preparedStmt.setInt   (2, product.getId());	      
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
	public Item get(int id) {
		Product product =null;
		try 
		{	
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from products where ProductId = " + id;
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			product = new Product();
			while (rs.next())
			{				
				String[] strArr = new String[5];
				for (int i=1; i<=numCols; i++)
				{
					strArr[i] = rs.getString(i);
				}
				product.setId(Integer.parseInt(strArr[1]));
				product.setName(strArr[2]);				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Item> get() {
		ArrayList<Item> products = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from products";
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			products = new ArrayList<Item>();
			while (rs.next())
			{
				Product product = new Product();
				String[] strArr = new String[5];
				for (int i=1; i<=numCols; i++)
				{
					strArr[i] = rs.getString(i);
				}
				product.setId(Integer.parseInt(strArr[1]));
				product.setName(strArr[2]);
				products.add(product);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub	
	}
	public void addSupplier(int productId, int supplierId){
		ProductSupplierLinkModelDB linkMe = new ProductSupplierLinkModelDB();
		if(linkMe.link(productId, supplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}
	public void removeSupplier(int productId, int supplierId){
		ProductSupplierLinkModelDB unLinkMe = new ProductSupplierLinkModelDB();
		if(unLinkMe.unlink(productId, supplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}
}
