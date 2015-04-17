package travelexperts;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Window.Type;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class CustomersUI extends JFrame {

	private JPanel contentPane;
	private JTable tblCustomers;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private List<Customer> customers;
	private List<Agent> agents;
	private CustomerModelDB customerDB = new CustomerModelDB();
	private static AgentModelDB agentDB= new AgentModelDB();
	private JComboBox<String> cmbAgents;
	private DefaultComboBoxModel<String> cmbModel;
	static Agent deletedAgent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		deletedAgent = agentDB.get(1);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomersUI frame = new CustomersUI(deletedAgent);
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
	public CustomersUI(Agent agent) {
		deletedAgent=agent;
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
				setVisible(false);
			}
		});
		btnExit.setBounds(636, 232, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i < tblCustomers.getRowCount(); i++) {
					customers.get(i).setAgentId(getAgentIdByName(tblCustomers.getValueAt(i, 3).toString()));
					customerDB.save(customers.get(i));
				}
				JOptionPane.showMessageDialog(null, "Saved successfully.");		
				setVisible(false);
			}
		});
		btnSave.setBounds(10, 232, 89, 23);
		contentPane.add(btnSave);
	}
	
	private void init() {		
		//customers = customerDB.get();
		customers = customerDB.get(deletedAgent.getId());
		agents = agentDB.get();
		cmbModel = new DefaultComboBoxModel<String>(populateComboBoxItems(agents)); 
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
	
	private int getAgentIdByName(String name) {
		int ix = -1;
		for (int i=0; i < agents.size(); i++) {
			if (agents.get(i).toString().equals(name)) {
				ix = agents.get(i).getId();				
				break;
			}
		}
		return ix;
	}
}
//JOptionPane.showMessageDialog(null, item, "Summary", JOptionPane.INFORMATION_MESSAGE);