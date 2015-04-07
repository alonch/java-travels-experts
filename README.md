
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class GUISuppliersTEx extends JFrame {
		/**
	 * 
	 */
	public static final long serialVersionUID = 7155368193318831729L;
		private JPanel contentPane;
		private JComboBox cbSupplierId;
		private ComboBoxModel cbSupplierIdModel;
		private JTextField txtSupName = new JTextField(20);	
		private JLabel lblSupplierId = new JLabel();
	    private JLabel lblSupName = new JLabel();
		private JButton btnUpdate;
		private JButton btnSave;
		private JButton btnLoadData;
		private Connection conn;
		private Statement stmt;
		private ResultSet rs;
		private JTable table;
	/**
	 * Create the frame.
	 */
	public GUISuppliersTEx() {		
		connect();	//connect method	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		getContentPane().setLayout(null);		
		cbSupplierIdModel = 
				new DefaultComboBoxModel(getSupplierIDs());
			cbSupplierId = new JComboBox();
			cbSupplierId.setModel(cbSupplierIdModel);
			cbSupplierId.setBounds(145, 12, 200, 31);
			getContentPane().add(cbSupplierId);			
			cbSupplierId.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					getSupplier();
				}
			});			
			txtSupName.setBounds(145, 52,200, 31);
			getContentPane().add(txtSupName);
			txtSupName.setEnabled(false);							
			{	
				{
					getContentPane().add(lblSupplierId);
					lblSupplierId.setText("Supplier ID:");
					lblSupplierId.setBounds(25, 20, 110, 18);
				}	
				{
				getContentPane().add(lblSupName);
				lblSupName.setText("Supplier Name:");
				lblSupName.setBounds(25, 60, 110, 18);	
				}
				{
					btnUpdate = new JButton();
					getContentPane().add(btnUpdate);
					btnUpdate.setText("Edit");
					btnUpdate.setBounds(194, 289, 100, 28);					
				btnUpdate.addMouseListener(new MouseAdapter()
				{				
					public void mouseClicked(MouseEvent e)
						{							
							btnUpdate.setEnabled(false); //disable update button
							txtSupName.requestFocusInWindow(); //focus set on supplier name
							txtSupName.setEnabled(true);
							btnSave.setEnabled(true);						
						}
				});			
					btnSave = new JButton();
					getContentPane().add(btnSave);
					btnSave.setText("Save");
					btnSave.setBounds(194, 329, 100, 28);
					
					table = new JTable();
					table.setBounds(25, 96, 375, 180);
					getContentPane().add(table);
					
					btnLoadData = new JButton("Load");
					btnLoadData.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{							
							/*try {
																
								Class.forName("oracle.jdbc.driver.OracleDriver");
								//Class.forName("com.mysql.jdbc.Driver");
								//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
								conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");		
								conn.setAutoCommit(false);
								stmt = conn.createStatement(); //this block code could be moved into updateSupplier method later on. It does not matter now.
								ResultSet rs;
								rs = stmt.executeQuery("select * from suppliers");
								TableModel model = DbUtils.resultSetToTableModel(rs);
								Suppliers.setModel(model);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						*/
						}
					});
					btnLoadData.setBounds(25, 331, 97, 25);
					getContentPane().add(btnLoadData);
					
					btnSave.addMouseListener(new MouseAdapter()
					{
						public void mouseClicked(MouseEvent e)
						{
							btnSave.setEnabled(false);
							btnUpdate.setEnabled(true);					
								try {
									GUISuppliersTEx.this.updateSupplier();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
				}
		}		
			setSize(452, 450);
			setVisible(true);	
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame GUISuppliersTEx = new GUISuppliersTEx();
					GUISuppliersTEx.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //print exceptions errors stack
				}
			}
		});
	}//end main	
	//connecting to the Travel Experts database
	public void connect()
	{
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");		
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
	//update statement for agents table
	public void updateSupplier() throws SQLException, ClassNotFoundException
	{			
		String sql = "UPDATE suppliers set "
	        	+ "SupName='" + txtSupName.getText()
	        	+ " WHERE SupplierId=" + cbSupplierId.getSelectedItem(); 
        int numRows;
		try {
			numRows = stmt.executeUpdate(sql); // will show if row updated
			conn.commit();
			if (numRows == 0)
			{
				System.out.println("update Supplier failed");
			}
			else
			{
				System.out.println("updated " + numRows + " row(s)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try
		{
			ResultSet rs;
				rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData(); //MetaData which gives column count
				int numCols = rsmd.getColumnCount();
				while (rs.next())
				{
					for (int i=1; i<=numCols; i++)
					{
						System.out.print(rs.getString(i) + "\t");
					}
							System.out.println();
				}
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private Vector<String> getSupplierIDs() {
		// TODO Auto-generated method stub
		Vector<String> suppliers = new Vector<String>();
		try {
			rs = stmt.executeQuery("select supplierid from suppliers");
			while (rs.next())
			{
				suppliers.add(rs.getString("supplierid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suppliers;
	}
	protected void getSupplier()
	{
		// TODO Auto-generated method stub
	try {
			rs = stmt.executeQuery("SELECT * FROM suppliers WHERE SupplierId=" + cbSupplierId.getSelectedItem());
			if (rs.next())
				{
					txtSupName.setText(rs.getString(2));
					//table.setText(rs.getString(getSuppliersIdName().row))
					
				}
			else
				{
					System.out.println("No row was returned");
				}
					rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
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
	
}//end class


