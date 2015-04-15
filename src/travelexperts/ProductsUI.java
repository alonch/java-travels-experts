package travelexperts;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;


public class ProductsUI extends JFrame {

	private JPanel contentPane;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExit;
	private JTable tblProducts;
	private JScrollPane scrlPaneProducts;
	private DefaultTableModel dtm;
	private List<Item> itemProducts;
	private ProductModelDB productDB;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsUI frame = new ProductsUI();
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
	public ProductsUI() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Travel Experts - Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prdName = JOptionPane.showInputDialog(null, "Product name?", "", JOptionPane.QUESTION_MESSAGE);
				if(prdName != null && !prdName.isEmpty()) {
					prdName = prdName.trim();
					Item p = new Item();
					p.setName(prdName);
					productDB.add(p);
					refreshList();
				}
			}
		});
		btnAdd.setBounds(263, 11, 89, 23);
		contentPane.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tblProducts.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Please select a product first.");
					return;
				}
				Item p = itemProducts.get(rowIndex);
				String prdName = JOptionPane.showInputDialog(null, "Product name?", p.getName());
				if(prdName != null && !prdName.isEmpty()) {
					prdName = prdName.trim();
					p.setName(prdName);
					productDB.save(p);
					refreshList();
				}
			}
		});
		btnEdit.setBounds(263, 45, 89, 23);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tblProducts.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Please select a product first.");
					return;
				}
				Item p = itemProducts.get(rowIndex);
				int dialogResult = JOptionPane.showConfirmDialog (null, "Confirm to delete?" + "\n" + p.getName(), "Confirmation", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					productDB.delete(p.getId());
					refreshList();
				}
			}
		});
		btnDelete.setBounds(263, 79, 89, 23);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(263, 228, 89, 23);
		contentPane.add(btnExit);
		
		scrlPaneProducts = new JScrollPane();
		scrlPaneProducts.setBounds(10, 11, 243, 240);
		contentPane.add(scrlPaneProducts);
		
		productDB = new ProductModelDB();
		itemProducts = productDB.get();
				
		setDTM();
		populateDTM();
		
		tblProducts = new JTable(dtm);		
		tblProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				//JOptionPane.showMessageDialog(null, dtm.getRowCount());
			}
		});
		tblProducts.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblProducts.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrlPaneProducts.setViewportView(tblProducts);			
	}
	
	private void refreshList() {
		itemProducts = null;
		itemProducts = productDB.get();
		clearDTM();
	}
	
	private void populateDTM() {		
		for (int i=0; i < itemProducts.size(); i++) {
			Object [] row = {itemProducts.get(i).getId(), itemProducts.get(i).getName()};
			dtm.addRow(row);
		}
	}
	
	private void clearDTM() {
		dtm = null;
		setDTM();
		populateDTM();
		tblProducts.setModel(dtm);
		tblProducts.getColumnModel().getColumn(0).setPreferredWidth(25);		
	}
	
	private void setDTM() {
		dtm = new DefaultTableModel();
		dtm.setColumnCount(2);		
		String [] colNames = {"Id", "Name"};
		dtm.setColumnIdentifiers(colNames);		
	}
}
