package travelexperts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class SupplierModelDB implements ItemModel {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	@Override
	public void add(Item product) {
		connect();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into suppliers(supplierid, supname) values (null, ?)");
			ps.setString(1, product.getName());
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
	public void save(Item supplier) {
		try
	    {
			conn = TravelExpertsDB.GetConnection();
			String query = "update suppliers set SupName ='"+supplier.getName()+"' where SupplierId ="+supplier.getId();
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			//preparedStmt.setString(1, supplier.getName());	      
			//preparedStmt.setInt   (2, supplier.getId());	   
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
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
	public List<Item> get() {
		connect();
		ArrayList<Item> suppliers = new ArrayList<Item>();
		try {
			PreparedStatement ps = conn.prepareStatement("select supplierid, supName from suppliers");
			rs = ps.executeQuery();
			while (rs.next())
			{
				//int supplierId = rs.getInt(1);
                //String supName = rs.getString(2);
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
	public void addProduct(int supplierId, int productId){
		ProductSupplierLinkModelDB linkMe = new ProductSupplierLinkModelDB();
		if(linkMe.link(productId, supplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}
	public void removeProduct(int supplierId, int productId){
		ProductSupplierLinkModelDB unLinkMe = new ProductSupplierLinkModelDB();
		if(unLinkMe.unlink(productId, supplierId)){
			//link successful
		}
		else{
			//link not successful
		}
	}

	public List<Product> getProducts(int supplierId){
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			String sql = "select * from products p, products_suppliers ps "
					+ "where ps.ProductId=p.ProductId and "
					+ " ps.SupplierId="+supplierId;
			rs = stmt.executeQuery(sql);
			//ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next())
			{
				Product product= new Product();
				
				product.setId(rs.getInt("ProductId"));
				product.setName(rs.getString("ProdName"));
				
				products.add(product);
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	public List<Product> getProductsNotLinked(int supplierId){
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			conn = TravelExpertsDB.GetConnection();
			stmt = conn.createStatement();
			
			String notIn = "select p.productid from products p, products_suppliers ps "
					+ "where ps.ProductId=p.ProductId and "
					+ " ps.SupplierId="+supplierId;
			
			String sql = "select * from products where productId not in (" + notIn + ") order by prodname"; 
			
			rs = stmt.executeQuery(sql);
			//ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next())
			{
				Product product= new Product();
				
				product.setId(rs.getInt("ProductId"));
				product.setName(rs.getString("ProdName"));
				
				products.add(product);
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
