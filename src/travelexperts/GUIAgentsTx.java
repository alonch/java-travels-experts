package travelexperts;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUIAgentsTx extends JFrame {

		/**
	 * 
	 */
	public static final long serialVersionUID = 7155368193318831719L;
		private JPanel contentPane;
		private JComboBox cbAgent;
		private ComboBoxModel cbAgentModel;
		private JComboBox cbAgency;
		private ComboBoxModel cbAgencyModel;
		private JTextField txtAgentId = new JTextField(5);
		private JTextField txtAgencyId = new JTextField(5);
		private JTextField txtFirst = new JTextField(20);
		private JTextField txtLast = new JTextField(20);
		private JTextField txtMidInit = new JTextField(5);
		private JTextField txtBusPhone = new JTextField(20);
		private JTextField txtEmail = new JTextField(25);
		private JTextField txtPosition = new JTextField(20);
		private JLabel lblAgentId = new JLabel();
		private JLabel lblAgencyId = new JLabel();
		private JLabel lblAgent = new JLabel();
		private JLabel lblBusinessPhone = new JLabel();
		private JLabel lblEmail = new JLabel();
		private JLabel lblFirstName = new JLabel();
		private JLabel lblLastName = new JLabel();
		private JLabel lblMI = new JLabel();
		private JLabel lblPosition = new JLabel();
		private JButton btnUpdate;
		private JButton btnSave;
		private JButton btnAdd;
		private JButton btnDelete;
		
		AgentModelDB agentdb = new AgentModelDB();
		private Agent agent;
		private ArrayList<Agent> agents;
		
		AgencyModelDB agencydb = new AgencyModelDB();
		private Agency agency;
		private ArrayList<Agency> agencies;
	/**
	 * Create the frame
	 */
	//@SuppressWarnings("unchecked")
	public GUIAgentsTx() {
		setTitle("Travel Experts: Agents");		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
		getContentPane().setLayout(null);	
		
		agents = (ArrayList<Agent>) agentdb.get();
		
		cbAgentModel = 
				new DefaultComboBoxModel();
			cbAgent = new JComboBox();
			cbAgent.setModel(cbAgentModel);
			for(Agent agent:agents){
				cbAgent.addItem(agent);
			}
			cbAgent.setBounds(145, 12, 150, 21);
			getContentPane().add(cbAgent);		
	
			cbAgent.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					agent = (Agent) cbAgent.getSelectedItem();
					System.out.println(agent);
					agency = (Agency) agencydb.get(agent.getAgencyId());
					
					txtAgentId.setText(""+agent.getId());
					txtFirst.setText(agent.getFirstName());	
					txtMidInit.setText(agent.getMiddleInitial());
					txtLast.setText(agent.getLastName());
					txtBusPhone.setText(agent.getBusPhone());
					txtEmail.setText(agent.getEmail());									
					txtPosition.setText(agent.getPosition());										
					cbAgency.setSelectedItem(agency);
				}
			});				
			txtAgentId.setEditable(false);
			txtAgentId.setBounds(145, 42,150, 21);
			getContentPane().add(txtAgentId);
			txtAgentId.setEnabled(false);
			
			txtFirst.setBounds(145, 72,150, 21);
			getContentPane().add(txtFirst);
			txtFirst.setEnabled(false);
			
			txtMidInit.setBounds(145, 102,150, 21);
			getContentPane().add(txtMidInit);
			txtMidInit.setEnabled(false);
			
			txtLast.setBounds(145, 132,150, 21);
			getContentPane().add(txtLast);
			txtLast.setEnabled(false);									
			
			txtBusPhone.setBounds(145, 162,150, 21);
			getContentPane().add(txtBusPhone);
			txtBusPhone.setEnabled(false);
								
			txtEmail.setBounds(145, 192,150, 21);
			getContentPane().add(txtEmail);	
			txtEmail.setEnabled(false);	
					
			txtPosition.setBounds(145, 222,150, 21);
			getContentPane().add(txtPosition);
			txtPosition.setEnabled(false);
					
			agencies = (ArrayList<Agency>) agencydb.get();
			
			cbAgencyModel = 
					new DefaultComboBoxModel();
			
				cbAgency = new JComboBox();
				cbAgency.setModel(cbAgencyModel);
				for(Agency agency:agencies){
					cbAgency.addItem(agency);
				}
				cbAgency.setBounds(145, 252,150, 21);
				getContentPane().add(cbAgency);			
				cbAgency.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{						
					}
				});		
			{
				getContentPane().add(lblAgent);
				lblAgent.setText("Agent:");
				lblAgent.setBounds(25, 15, 110, 14);
			}			
			{
				getContentPane().add(lblAgentId);
				lblAgentId.setText("Agent ID:");
				lblAgentId.setBounds(25, 45, 110, 14);
			}		
			{
				getContentPane().add(lblFirstName);
				lblFirstName.setText("First Name:");
				lblFirstName.setBounds(25, 75, 110, 14);
			}
			{
				getContentPane().add(lblMI);
				lblMI.setText("Middle Initial:");
				lblMI.setBounds(25, 105, 110, 14);
			}	
			{
				getContentPane().add(lblLastName);
				lblLastName.setText("Last Name:");
				lblLastName.setBounds(25, 135, 110, 14);
			}								
			{
				getContentPane().add(lblBusinessPhone);
				lblBusinessPhone.setText("Business Phone:");
				lblBusinessPhone.setBounds(25, 165, 110, 14);
			}
			{
				getContentPane().add(lblEmail);
				lblEmail.setText("Email:");
				lblEmail.setBounds(25, 195, 110, 14);
			}
			{
				getContentPane().add(lblPosition);
				lblPosition.setText("Position:");
				lblPosition.setBounds(25, 225, 110, 14);
			}
			{				
				getContentPane().add(lblAgencyId);			
				lblAgencyId.setText("Agency:");				
				lblAgencyId.setBounds(25, 255, 110, 14);									
				{
					btnUpdate = new JButton();
					getContentPane().add(btnUpdate);
					btnUpdate.setText("Edit");
					btnUpdate.setBounds(359, 30, 80, 21);					
				btnUpdate.addMouseListener(new MouseAdapter()
				{				
					public void mouseClicked(MouseEvent e)
						{							
							btnUpdate.setEnabled(false); //disable update button
							txtFirst.requestFocusInWindow(); //focus set on agtFirstName
							txtFirst.setEnabled(true);
							txtMidInit.setEnabled(true);
							txtLast.setEnabled(true);
							txtBusPhone.setEnabled(true);
							txtEmail.setEnabled(true);
							txtPosition.setEnabled(false);
							txtAgencyId.setEnabled(true);
							btnSave.setEnabled(true);		
							btnAdd.setEnabled(true);
						}
				});			
					btnSave = new JButton();
					getContentPane().add(btnSave);
					btnSave.setText("Save");
					btnSave.setBounds(359, 87, 80, 21);			
					btnSave.addMouseListener(new MouseAdapter()
					{
						public void mouseClicked(MouseEvent e)
						{
							btnSave.setEnabled(false);
							btnUpdate.setEnabled(true);	
							btnAdd.setEnabled(true);
							//validate data
							
							if (Validator.notEmpty(txtFirst) && 
									Validator.notEmpty(txtMidInit) &&
									Validator.notEmpty(txtLast) &&									
									Validator.notEmpty(txtBusPhone) &&
									Validator.notEmpty(txtEmail) &&
									Validator.notEmpty(txtPosition) &&
									Validator.notEmpty(txtEmail)){
								agent=new Agent();
								agent.setId(Integer.parseInt(txtAgentId.getText()));
								agent.setFirstName(txtFirst.getText());
								agent.setMiddleInitial(txtMidInit.getText());
								agent.setLastName(txtLast.getText());
								agent.setBusPhone(txtBusPhone.getText());
								agent.setEmail(txtEmail.getText());							
								agent.setPosition(txtPosition.getText());
								agentdb.save(agent);
							}
						}
					});					
					btnAdd = new JButton();
					getContentPane().add(btnAdd);
					btnAdd.setText("Add");
					btnAdd.setBounds(359, 156, 80, 21);			
					btnAdd.addMouseListener(new MouseAdapter()
					{
						public void mouseClicked(MouseEvent e)
						{
							btnAdd.setEnabled(false);
							btnUpdate.setEnabled(true);	
							btnSave.setEnabled(true);
								try {																																	
									txtAgentId.setText(""+agent.getId()); 
									txtFirst.setText("");
									txtMidInit.setText("");
									txtLast.setText("");
									txtBusPhone.setText("");
									txtEmail.setText("");									
									txtPosition.setText("");	
									txtAgencyId.setText("");								
																
									txtFirst.requestFocusInWindow(); 
									txtAgentId.setEnabled(false);
									txtFirst.setEnabled(true);
									txtMidInit.setEnabled(true);
									txtLast.setEnabled(true);
									txtBusPhone.setEnabled(true);
									txtEmail.setEnabled(true);
									txtPosition.setEnabled(true);
									txtAgencyId.setEnabled(true);							
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					btnDelete = new JButton("Delete");
					btnDelete.setBounds(359, 222, 80, 24);
					getContentPane().add(btnDelete);
					btnDelete.addMouseListener(new MouseAdapter()
					{
						public void mouseClicked(MouseEvent e)
						{
							btnAdd.setEnabled(true);
							btnUpdate.setEnabled(true);	
							btnSave.setEnabled(true);
							btnDelete.setEnabled(false);
							try {
							//delete the agent
							agentdb.delete(agent.getId());
								
							//open table of customers assigned to that agent
							new CustomersUI(agent).setVisible(true);;
								
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			});
				}
		}		
			setSize(550, 339);
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
					JFrame GUIAgentsTx = new GUIAgentsTx();
					GUIAgentsTx.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //print exceptions errors stack
				}
			}
		});
	}//end main	
}//end class

