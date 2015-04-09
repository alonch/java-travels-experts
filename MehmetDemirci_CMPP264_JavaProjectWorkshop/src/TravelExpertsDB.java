import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TravelExpertsDB {
	
	public static Connection GetConnection()
    {        
        Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return connection;
    }
}
