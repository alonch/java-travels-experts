package travelexperts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TravelExpertsDB {
	private static Connection instance = null;

	public static Connection GetConnection()
    {   try{
        if (instance == null || instance.isClosed()){
        	instance = GET_INSTANCE();
        }
    }catch(Exception ex){
    }
        return instance;
    }

	private static Connection GET_INSTANCE() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/travelexperts", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
