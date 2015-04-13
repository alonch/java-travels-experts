import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class SupplierModelDB implements SupplierModel {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public void save(Supplier supplier) {
		connect();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into suppliers(supplierid, supname) values (?, ?)");
			ps.setInt(1, supplier.getId());
			ps.setString(2, supplier.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Supplier get(int id) {
		connect();
		try {
			PreparedStatement ps = conn.prepareStatement("select supplierid, supName from suppliers where supplierid=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				Supplier supplier = new Supplier(); 
				supplier.setId(rs.getInt(1)); 
				supplier.setName(rs.getString(2));	
			    return supplier;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		throw new NoSuchElementException("supplier "+ id + " not found");
	}

	@Override
	public void delete(int id) {
		connect();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from suppliers where SupplierId = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		
	}
	
	public void connect()
	{
        try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexpertsjava","root","");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");		
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end connect

	@Override
	public List<Supplier> get() {
		connect();
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			PreparedStatement ps = conn.prepareStatement("select supplierid, supName from suppliers");
			rs = ps.executeQuery();
			while (rs.next())
			{
				int supplierId = rs.getInt(1);
                String supName = rs.getString(2);
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt(1));
				supplier.setName(rs.getString(2));
    		    suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return suppliers;
	}

	@Override
	public void reset() {
		connect();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from suppliers");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}