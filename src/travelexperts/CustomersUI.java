package travelexperts;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Window.Type;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class CustomersUI extends JFrame {

	private JPanel contentPane;
	private JTable tblCustomers;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private List<Customer> customers;
	private List<Agent> agents;
	private CustomerModelDB customerDB;
	private AgentModelDB agentDB;
	private JComboBox<String> cmbAgents;
	private DefaultComboBoxModel<String> cmbModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomersUI frame = new CustomersUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomersUI() {
		setType(Type.UTILITY);
		setTitle("Travel Experts - Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		init();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 715, 210);
		contentPane.add(scrollPane);
		
		
		tblCustomers = new JTable(dtm);
		tblCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblCustomers);
		TableColumn columnAgent = tblCustomers.getColumnModel().getColumn(3);
		columnAgent.setCellEditor(new DefaultCellEditor(cmbAgents));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(636, 232, 89, 23);
		contentPane.add(btnExit);
	}
	
	private void init() {		
		customerDB = new CustomerModelDB();
		customers = customerDB.get();
		agentDB = new AgentModelDB();
		agents = agentDB.get();
		cmbModel = new DefaultComboBoxModel<String>(populateComboBoxItems(agents)); 
		//cmbAgents = new JComboBox<String>(populateComboBoxItems(agents));
		cmbAgents = new JComboBox<String>();
		cmbAgents.setModel(cmbModel);		
		setDTM();
		populateDTM();
	}
	
	private void setDTM() {
		dtm = new DefaultTableModel();
		dtm.setColumnCount(4);		
		String [] colNames = {"Name", "Phone", "Email", "Agent"};
		dtm.setColumnIdentifiers(colNames);
		
	}
	
	private void populateDTM() {
		
		for (int i=0; i < customers.size(); i++) {	
			
			String agentName = "";
			for (int k=0; k < agents.size(); k++) {
				if (customers.get(i).getAgentId() == agents.get(k).getId())
					agentName = agents.get(k).toString();
			}
			
			Object [] row = {customers.get(i).toString(), customers.get(i).getBusPhone(), customers.get(i).getEmail(), agentName};
			dtm.addRow(row);
		}
	}
	
	private Vector<String> populateComboBoxItems(List<Agent> agentList)
	{
		Vector<String> items = new Vector<String>();		
		for (int i=0; i < agentList.size(); i++)
			items.add(agentList.get(i).toString());
		return items;
	}
}
//JOptionPane.showMessageDialog(null, item, "Summary", JOptionPane.INFORMATION_MESSAGE);