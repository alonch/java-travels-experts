package travelexperts;
import java.util.ArrayList;
import java.util.Collections;
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
	private boolean isForProducts;
	
	private ProductModelDB productdb=new ProductModelDB();
	private DefaultListModel<Item> modelIncluded;
	private DefaultListModel<Item> modelNotIncluded;
	
	private SupplierModelDB supplierdb=new SupplierModelDB();
	
	

	public ItemMapperUI(boolean isForProducts, Item item) {
		this.item=item;
		this.isForProducts = isForProducts;
		
		setTitle("Travel Experts: Product and Supplier Maintenance");	
		setAutoRequestFocus(false);
		getContentPane().setLayout(null);
		setSize(new Dimension(800,600) );
		table = new JTable();
		table.setBounds(0, 22, 0, 442);
		getContentPane().add(table);		
		
		if(isForProducts){
			modelIncluded = new DefaultListModel<Item>();
			itemsIn = new JList<Item>(modelIncluded);
			
			modelNotIncluded = new DefaultListModel<Item>();
			itemsNotIn = new JList<Item>(modelNotIncluded);
		}
		else{
			//for suppliers. choose products of suppliers
			modelIncluded = new DefaultListModel<Item>();
			itemsIn = new JList<Item>(modelIncluded);
			
			modelNotIncluded = new DefaultListModel<Item>();
			itemsNotIn = new JList<Item>(modelNotIncluded);
		}
		
		
		//itemsIn = new JList();
		itemsIn.setBounds(12, 100, 338, 280);
		getContentPane().add(itemsIn);
		
		//itemsNotIn = new JList();
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
		setItemName();
		
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
		
		//get itemsin and items not in
		if(isForProducts){
			//suppliers linked to product
			getSuppliersLinkedToProduct();
			
			//suppliers not linked to product
			getSuppliersNotLinkedToProduct();
			
		}else{
			//products linked to supplier
			getProductsLinkedToSupplier();
			
			//products not linked to supplier
			getProductsNotLinkedToSupplier();
			
			
		}
	}
	private void getSuppliersNotLinkedToProduct() {
		modelNotIncluded.removeAllElements();
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers = (ArrayList<Supplier>) productdb.getSuppliersNotLinked(item.getId());
		//Collections.sort(suppliers);
		for(Supplier s: suppliers){
			modelNotIncluded.addElement(s);
		}
	}
	private void getSuppliersLinkedToProduct() {
		modelIncluded.removeAllElements();
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers = (ArrayList<Supplier>) productdb.getSuppliers(item.getId());
		//Collections.sort((List<Supplier>) suppliers);
		for(Supplier s: suppliers){
			modelIncluded.addElement(s);
		}
	}
	private void getProductsNotLinkedToSupplier() {
		modelNotIncluded.removeAllElements();
		ArrayList<Product> products = new ArrayList<Product>();
		products= (ArrayList<Product>) supplierdb.getProductsNotLinked(item.getId());
		//Collections.sort(suppliers);
		for(Product p: products){
			modelNotIncluded.addElement(p);
		}
	}
	private void getProductsLinkedToSupplier() {
		modelIncluded.removeAllElements();
		ArrayList<Product> products = new ArrayList<Product>();
		products = (ArrayList<Product>) supplierdb.getProducts(item.getId());
		//Collections.sort((List<Supplier>) suppliers);
		for(Product p: products){
			modelIncluded.addElement(p);
		}
	}
	public void setItemName(){
		itemName.setText(item.toString());
	}	
	/*public void setItemsIn(List<Item> items){
		itemsIn.setModel(new DefaultComboBoxModel(items.toArray()));
	}
	public void setItemsNotIn(List<Item> items){
		itemsNotIn.setModel(new DefaultComboBoxModel(items.toArray()));
	}*/
	
	public void setLabelsToSuppliers(){
		lblProductSupplierIn.setText("Suppliers in:");
		lblProductSupplierNot.setText("Suppliers not in:");
	}
	
	public void setLabelsToProducts(){
		lblProductSupplierIn.setText("Products in:");
		lblProductSupplierNot.setText("Products not in:");
	}
}
