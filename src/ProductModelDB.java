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
		conn = TravelExpertsDB.GetConnection();
		
		try
        {  
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
		conn = TravelExpertsDB.GetConnection();
		
		try
	    { 
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
		Product product = new Product();		
		conn = TravelExpertsDB.GetConnection();
		
		try 
		{
			stmt = conn.createStatement();
			String sql = "select * from products where ProductId = " + id;
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
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
		ArrayList<Item> products = new ArrayList<Item>();
		
		conn = TravelExpertsDB.GetConnection();
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from products";
			rs = stmt.executeQuery(sql);
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
}