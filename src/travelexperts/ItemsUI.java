package travelexperts;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

import javax.swing.ListSelectionModel;

public class ItemsUI extends JFrame {

	private JPanel contentPane;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExit;
	private JTable tblItems;
	private JScrollPane scrlPaneProducts;
	private DefaultTableModel dtm;
	private List<Item> items;
	private ItemModel model;
	private boolean isForProducts;
	private ItemsUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					boolean isForProducts = false;
					ItemsUI frame;
					frame = new ItemsUI(isForProducts);
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
	public ItemsUI(boolean isForProducts) {
		this.isForProducts = isForProducts;
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Travel Experts - Items UI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 368, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prdName = JOptionPane.showInputDialog(null,
						"Product name?", "", JOptionPane.QUESTION_MESSAGE);
				if (prdName != null && !prdName.isEmpty()) {
					prdName = prdName.trim();
					Item p = new Item();
					p.setName(prdName);
					model.add(p);
					refreshList();
				}
			}
		});
		btnAdd.setBounds(263, 11, 89, 23);
		contentPane.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tblItems.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(null,
							"Please select an item first.");
					return;
				}
				Item item;
				ItemMapperUI itemMapperUI;
				int id = items.get(rowIndex).getId();
				item = model.get(id);
				itemMapperUI = new ItemMapperUI(isForProducts, item);
				
				if (isForProducts) {		        
					itemMapperUI.setLabelsToSuppliers();
				} else {
					itemMapperUI.setLabelsToProducts();
				}
				itemMapperUI.setModal(true);
				itemMapperUI.setVisible(true);
			}
		});
		btnEdit.setBounds(263, 45, 89, 23);
		contentPane.add(btnEdit);

		//btnDelete = new JButton("Delete");
		/*btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = tblItems.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(null,
							"Please select a product first.");
					return;
				}
				Item p = items.get(rowIndex);
				int dialogResult = JOptionPane.showConfirmDialog(null,
						"Confirm to delete?" + "\n" + p.getName(),
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					model.delete(p.getId());
					refreshList();
				}
			}
		});
		btnDelete.setBounds(263, 79, 89, 23);
		contentPane.add(btnDelete);*/

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				setVisible(false);
			}
		});
		btnExit.setBounds(263, 228, 89, 23);
		contentPane.add(btnExit);

		scrlPaneProducts = new JScrollPane();
		scrlPaneProducts.setBounds(10, 11, 243, 240);
		contentPane.add(scrlPaneProducts);
		if (isForProducts) {
			model = new ProductModelDB();
		} else {
			model = new SupplierModelDB();
		}

		items = model.get();

		setDTM();
		populateDTM();

		tblItems = new JTable(dtm);
		tblItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// JOptionPane.showMessageDialog(null, dtm.getRowCount());
			}
		});
		tblItems.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblItems.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrlPaneProducts.setViewportView(tblItems);
	}

	private void refreshList() {
		items = null;
		items = model.get();
		clearDTM();
	}

	private void populateDTM() {
		for (int i = 0; i < items.size(); i++) {
			Object[] row = { items.get(i).getId(), items.get(i).getName() };
			dtm.addRow(row);
		}
	}

	private void clearDTM() {
		dtm = null;
		setDTM();
		populateDTM();
		tblItems.setModel(dtm);
		tblItems.getColumnModel().getColumn(0).setPreferredWidth(25);
	}

	private void setDTM() {
		dtm = new DefaultTableModel();
		dtm.setColumnCount(2);
		String[] colNames = { "Id", "Name" };
		dtm.setColumnIdentifiers(colNames);
	}
}
