import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AgencyModelDB implements AgencyModel{

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public Agency get(int id) {
		Agency agency=null;
		try 
		{	
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from agencies where AgencyId = " + id;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				agency = new Agency(); 
				agency.setId(rs.getInt("AgencyId"));
				agency.setAddress(rs.getString("AgncyAddress"));
				agency.setCity(rs.getString("AgncyCity"));
				agency.setProvince(rs.getString("AgncyProv"));
				agency.setPostalCode(rs.getString("AgncyPostal"));
				agency.setCountry(rs.getString("AgncyCountry"));
				agency.setPhone(rs.getString("AgncyPhone"));
				agency.setFax(rs.getString("AgncyFax"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agency;
	}

	@Override
	public List<Agency> get() {
		ArrayList<Agency> agencies = null;
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from agencies";
			rs = stmt.executeQuery(sql);
			agencies = new ArrayList<Agency>();
			while (rs.next())
			{
				Agency agency= new Agency();
				agency.setId(rs.getInt("AgencyId"));
				agency.setAddress(rs.getString("AgncyAddress"));
				agency.setCity(rs.getString("AgncyCity"));
				agency.setProvince(rs.getString("AgncyProv"));
				agency.setPostalCode(rs.getString("AgncyPostal"));
				agency.setCountry(rs.getString("AgncyCountry"));
				agency.setPhone(rs.getString("AgncyPhone"));
				agency.setFax(rs.getString("AgncyFax"));
				
				agencies.add(agency);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return agencies;
	}

}
