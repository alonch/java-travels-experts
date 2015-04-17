package travelexperts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AgentModelDB implements AgentModel{

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public void add(Agent agent) {
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String strSQL = "Insert Into agents Values("
					+ null +",'"+agent.getFirstName()+"' ,'"+agent.getMiddleInitial()+"','"+agent.getLastName()+"',"
					+ "'"+agent.getBusPhone()+"',"
					+ "'"+agent.getEmail()+"','"+agent.getPosition()+"',"+agent.getAgencyId()+","
					+ "'"+agent.getUserId()+"','"+agent.getPassword()+"')";

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
	public void save(Agent agent) {
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update agents "
					+ "set AgtFirstName = '"+agent.getFirstName()+"',"
					+ "AgtMiddleInitial = '"+agent.getMiddleInitial()+"',"
					+ "AgtLastName = '"+agent.getLastName()+"',"
					+ "AgtBusPhone = '"+agent.getBusPhone()+"',"
					+ "AgtEmail = '"+agent.getEmail()+"',"
					+ "AgtPosition = '"+agent.getPosition()+"',"
					+ "AgencyId = "+agent.getAgencyId()+","
					+ "userid = '"+agent.getUserId()+"',"
					+ "password = '"+agent.getPassword()+"'"
					+ " where AgentId = "+agent.getId();
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
	public Agent get(int id) {
		Agent agent =null;
		try 
		{	
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from agents where AgentId = " + id;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				agent = new Agent(); 
				agent.setId(rs.getInt("AgentId"));
				agent.setFirstName(rs.getString("AgtFirstName"));
				agent.setMiddleInitial(rs.getString("AgtMiddleInitial"));
				agent.setLastName(rs.getString("AgtLastName"));
				agent.setBusPhone(rs.getString("AgtFirstName"));
				agent.setEmail(rs.getString("AgtEmail"));
				agent.setPosition(rs.getString("AgtPosition"));
				agent.setAgencyId(rs.getInt("AgencyId"));
				agent.setUserId(rs.getString("userid"));
				agent.setPassword(rs.getString("password"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agent;
	}

	@Override
	public List<Agent> get() {
		ArrayList<Agent> agents = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from agents where deleted=0";
			rs = stmt.executeQuery(sql);
			agents = new ArrayList<Agent>();
			while (rs.next())
			{
				Agent agent= new Agent();
				agent.setId(rs.getInt("AgentId"));
				agent.setFirstName(rs.getString("AgtFirstName"));
				agent.setMiddleInitial(rs.getString("AgtMiddleInitial"));
				agent.setLastName(rs.getString("AgtLastName"));
				agent.setBusPhone(rs.getString("AgtFirstName"));
				agent.setEmail(rs.getString("AgtEmail"));
				agent.setPosition(rs.getString("AgtPosition"));
				agent.setAgencyId(rs.getInt("AgencyId"));
				agent.setUserId(rs.getString("userid"));
				agent.setPassword(rs.getString("password"));
				
				agents.add(agent);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return agents;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		//set deleted
		//customers assigned to this agent will get a new agent
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update agents "
					+ "set deleted = "+1
					+ " where AgentId = "+id;
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

}
