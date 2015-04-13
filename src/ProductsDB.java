import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

// Team 4 - Mehmet Demirci 

public class ProductsDB {
	
	public static Vector<Product> GetProducts()
	{
		Vector<Product> products = new Vector<Product>();
		
		Connection conn = TravelExpertsDB.GetConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from products";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			while (rs.next())
			{
				Product product = new Product();
				String[] strArr = new String[5];
				for (int i=1; i<=numCols; i++)
				{
					strArr[i] = rs.getString(i);
				}
				product.setProductId(Integer.parseInt(strArr[1]));
				product.setProdName(strArr[2]);
				products.add(product);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return products;
	}

	public static Product GetProductByID(Integer ID)
	{
		Product product = new Product();		
		Connection conn = TravelExpertsDB.GetConnection();
		
		try 
		{
			Statement stmt = conn.createStatement();
			String sql = "select * from products where ProductId = " + ID.toString();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			while (rs.next())
			{				
				String[] strArr = new String[5];
				for (int i=1; i<=numCols; i++)
				{
					strArr[i] = rs.getString(i);
				}
				product.setProductId(Integer.parseInt(strArr[1]));
				product.setProdName(strArr[2]);				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	public static void UpdateProduct(Product product)
	{
		Connection conn = TravelExpertsDB.GetConnection();
		
		try
	    { 
	      String query = "update products set ProdName = ? where ProductId = ?";
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString(1, product.getProdName());	      
	      preparedStmt.setInt   (2, product.getProductId());	      
	      preparedStmt.executeUpdate();
	       
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void AddProduct(Product product)
	{
		Connection conn = TravelExpertsDB.GetConnection();
		
		try
        {  
			Statement stmt = conn.createStatement();

            String strSQL = "Insert Into products (ProdName) Values('" + product.getProdName() + "')";

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
	
	public static void DeleteProduct(Product product)
	{
		// Mark the product record as "deleted" by ID
	}
}
