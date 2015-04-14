import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerModelDB implements CustomerModel {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public void save(Customer customer) {
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update customers "
					+ "set AgentId = "+customer.getAgentId()
					+ " where CustomerId = "+customer.getId();
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
	public List<Customer> get(int agentId) {
		ArrayList<Customer> customers = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from customers where AgentId="+agentId;
			rs = stmt.executeQuery(sql);
			customers = new ArrayList<Customer>();
			while (rs.next())
			{
				Customer customer= new Customer();
				customer.setId(rs.getInt("CustomerId"));
				customer.setFirstName(rs.getString("CustFirstName"));
				customer.setLastName(rs.getString("CustLastName"));
				customer.setAddress(rs.getString("CustAddress"));
				customer.setCity(rs.getString("CustCity"));
				customer.setProvince(rs.getString("CustProv"));
				customer.setPostal(rs.getString("CustPostal"));
				customer.setCountry(rs.getString("CustCountry"));
				customer.setHomePhone(rs.getString("CustHomePhone"));
				customer.setBusPhone(rs.getString("CustBusPhone"));
				customer.setEmail(rs.getString("CustEmail"));
				customer.setAgentId(rs.getInt("AgentId"));
				
				customers.add(customer);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}

}
