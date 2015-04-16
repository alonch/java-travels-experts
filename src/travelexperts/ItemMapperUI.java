package travelexperts;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;



public class ItemMapperUI extends JDialog{

	private JTable table;
	//private JComboBox cbSupplier;
	//private ComboBoxModel cbSupplierProductModel;
	private JList itemsIn;
	private JList itemsNotIn;
	private JTextField itemName;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnAdd;
	private JLabel lblItem;
	private JLabel lblProductSupplierIn;
	private JLabel lblProductSupplierNot;
	private Item item;

	public ItemMapperUI(Item item) {
		this.item = item;
		setTitle("Travel Experts: Product and Supplier Maintenance");	
		setAutoRequestFocus(false);
		getContentPane().setLayout(null);
		setSize(new Dimension(800,600) );
		table = new JTable();
		table.setBounds(0, 22, 0, 442);
		getContentPane().add(table);		
				
		itemsIn = new JList();
		itemsIn.setBounds(12, 100, 338, 280);
		getContentPane().add(itemsIn);
		
		itemsNotIn = new JList();
		itemsNotIn.setBounds(405, 100, 330, 280);
		getContentPane().add(itemsNotIn);	
		
		btnSave = new JButton("Save");
		btnSave.setBounds(514, 431, 97, 33);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(107, 431, 97, 33);
		getContentPane().add(btnCancel);
		
		itemName = new JTextField();
		itemName.setBounds(288, 18, 214, 22);
		getContentPane().add(itemName);
		itemName.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(307, 431, 97, 33);
		getContentPane().add(btnAdd);
		
		lblItem = new JLabel("Item Name: ");
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItem.setBounds(119, 3, 200, 50);
		getContentPane().add(lblItem);
		
		lblProductSupplierIn = new JLabel("");
		lblProductSupplierIn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProductSupplierIn.setBounds(4, 30, 200, 57);
		getContentPane().add(lblProductSupplierIn);
		
		lblProductSupplierNot = new JLabel("");
		lblProductSupplierNot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProductSupplierNot.setBounds(405, 33, 232, 50);
		getContentPane().add(lblProductSupplierNot);
	}	
	public void setItem(Item item){
		itemName.setText(item.toString());
	}	
	public void setItemsIn(List<Item> items){
		itemsIn.setModel(new DefaultComboBoxModel(items.toArray()));
	}
	public void setItemsNotIn(List<Item> items){
		itemsNotIn.setModel(new DefaultComboBoxModel(items.toArray()));
	}
	
	public void setLabelsToSuppliers(){
		lblProductSupplierIn.setText("Suppliers in:");
		lblProductSupplierNot.setText("Suppliers not in:");
	}
	
	public void setLabelsToProducts(){
		lblProductSupplierIn.setText("Products in:");
		lblProductSupplierNot.setText("Products not in:");
	}
}
