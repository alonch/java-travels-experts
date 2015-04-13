import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SupplierTest {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	Supplier supplier;
	
	@Before
	public void setUp() throws Exception {
		supplier = new Supplier();
	}

	@Test
	public void testName() {
		supplier.setName("AirCanada");
		assertEquals("AirCanada", supplier.getName());
	}
	
	@Test
	public void testId() {
		supplier.setId(1);
		assertEquals(1, supplier.getId());
	}
	
	
	//connecting to the Travel Experts database
		public void connect()
		{
	        try {
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts-java","root","");
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");		
		        conn.setAutoCommit(false);
		        stmt = conn.createStatement(); //this block code could be moved into updateSupplier method later on. It does not matter now.
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end connect	
		
		private ArrayList<Object[]> getSuppliersIdName(){
			// TODO Auto-generated method stub
			ArrayList<Object[]> suppliers = new ArrayList<Object[]>();
			try {
				rs = stmt.executeQuery("select supplierid, supName from suppliers");
				while (rs.next())
				{
					int supplierId = rs.getInt(1);
	                String supName = rs.getString(2);
					System.out.println(supplierId + "   " + supName);               
				    Object[] row = new Object[]{rs.getInt(1), 
				                    rs.getString(2)};	
				    suppliers.add(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return suppliers;
		}

}
