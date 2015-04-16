package travelexperts;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class PackageGUI {

	public static NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
	public static NumberFormatter currencyF= new NumberFormatter(currency);
	
	private JFrame frame;
	private JTextField tfName;
	private JFormattedTextField tfBasePrice;
	private JFormattedTextField tfAgencyCommission;
	private JLabel lblId;
	private JDateChooser dcStartDate;
	private JDateChooser dcEndDate;
	private JTextArea taDescription;
	private JComboBox<Package> cbPackages;
	private JList<ProductSupplierLink> listNotIncludedProducts;
	private JList<ProductSupplierLink> listIncludedProducts;
	private DefaultListModel<ProductSupplierLink> modelIncluded;
	private DefaultListModel<ProductSupplierLink> modelNotIncluded;
				
	private Package pkg;
	private PackageModelDB pkgdb = new PackageModelDB();
	private ArrayList<Package> pkgs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		currencyF.setMinimum(0.00);
		currencyF.setMaximum(10000000.0);
		//currencyF.setAllowsInvalid(false);
		//currencyF.setOverwriteMode(true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageGUI window = new PackageGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PackageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 777);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JLabel lblSelectAPackage = new JLabel("Select a Package");
		lblSelectAPackage.setBounds(12, 26, 147, 16);
		frame.getContentPane().add(lblSelectAPackage);
		
		lblId = new JLabel("");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblId.setBounds(55, 91, 73, 33);
		frame.getContentPane().add(lblId);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(12, 153, 147, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(12, 189, 147, 16);
		frame.getContentPane().add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(12, 227, 147, 16);
		frame.getContentPane().add(lblEndDate);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(12, 268, 147, 16);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblBasePrice = new JLabel("Base Price");
		lblBasePrice.setBounds(411, 153, 147, 16);
		frame.getContentPane().add(lblBasePrice);
		
		JLabel lblAgencyCommission = new JLabel("Agency Commission:");
		lblAgencyCommission.setBounds(411, 189, 147, 16);
		frame.getContentPane().add(lblAgencyCommission);
		
		tfName = new JTextField();
		tfName.setBounds(152, 150, 207, 22);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		dcStartDate = new JDateChooser();
		dcStartDate.setForeground(Color.BLACK);
		dcStartDate.getDateEditor().setEnabled(false);
		dcStartDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dcEndDate.getJCalendar().setMinSelectableDate(dcStartDate.getDate());
			}
		});
		dcStartDate.setBounds(152, 183, 154, 22);
		frame.getContentPane().add(dcStartDate);
		
		dcEndDate = new JDateChooser();
		dcEndDate.setForeground(Color.BLACK);
		dcEndDate.getDateEditor().setEnabled(false);
		dcEndDate.setBounds(152, 221, 154, 22);
		frame.getContentPane().add(dcEndDate);
		taDescription = new JTextArea();
		taDescription.setBounds(152, 263, 521, 40);
		frame.getContentPane().add(taDescription);
		
		tfBasePrice = new JFormattedTextField(currencyF);
		tfBasePrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
					tfBasePrice.setValue(Double.parseDouble(tfBasePrice.getText()));
				}catch(Exception ex){
					ex.printStackTrace();
					tfBasePrice.setValue(0);
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				tfBasePrice.setText(""+tfBasePrice.getValue());
			}
		});
		tfBasePrice.setBounds(557, 150, 116, 22);
		frame.getContentPane().add(tfBasePrice);
		tfBasePrice.setColumns(10);
		
		tfAgencyCommission = new JFormattedTextField(currencyF);
		tfAgencyCommission.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfAgencyCommission.setText(""+tfAgencyCommission.getValue());
			}
			@Override
			
			public void focusLost(FocusEvent e) {
				
				try{
					tfAgencyCommission.setValue(Double.parseDouble(tfAgencyCommission.getText()));
				}catch(Exception ex){
					ex.printStackTrace();
					tfAgencyCommission.setValue(0);
				}
			}
		});
		tfAgencyCommission.setColumns(10);
		tfAgencyCommission.setBounds(557, 186, 116, 22);
		frame.getContentPane().add(tfAgencyCommission);
		
		JLabel lblProductsIncluded = new JLabel("Products Included");
		lblProductsIncluded.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductsIncluded.setBounds(31, 345, 154, 33);
		frame.getContentPane().add(lblProductsIncluded);
		
		modelIncluded = new DefaultListModel<ProductSupplierLink>();
		
		JLabel lblProductsNotIncluded = new JLabel("Products Not Included");
		lblProductsNotIncluded.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductsNotIncluded.setBounds(354, 345, 186, 33);
		frame.getContentPane().add(lblProductsNotIncluded);
		
		JLabel lblClickAProduct = new JLabel("double-click a product to remove");
		lblClickAProduct.setBounds(31, 375, 171, 16);
		frame.getContentPane().add(lblClickAProduct);
		
		JLabel lblClickAProduct_1 = new JLabel("double-click a product to add");
		lblClickAProduct_1.setBounds(354, 375, 171, 16);
		frame.getContentPane().add(lblClickAProduct_1);
		
		modelNotIncluded = new DefaultListModel<ProductSupplierLink>();
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(574, 707, 99, 25);
		frame.getContentPane().add(btnExit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(Validator.notEmpty(tfName) &&
						Validator.validDate(dcStartDate) &&
						Validator.validDate(dcEndDate) &&
						Validator.dateInRange(dcEndDate, dcStartDate) &&
						Validator.notEmpty(taDescription) &&
						Validator.numberIsDouble(tfBasePrice) &&
						Validator.doubleIsLessthan(tfBasePrice, 0, 99999999) &&
						Validator.numberIsDouble(tfAgencyCommission) &&
						Validator.doubleIsLessthan(tfAgencyCommission, 0, Double.parseDouble(tfBasePrice.getValue().toString()))){
					//valid user input
					
					pkg.setName(tfName.getText());
					pkg.setStartDate(dcStartDate.getDate());
					pkg.setEndDate(dcEndDate.getDate());
					pkg.setDescription(taDescription.getText());
					pkg.setBasePrice(Double.parseDouble(tfBasePrice.getValue().toString()));
					pkg.setAgencyCommission(Double.parseDouble(tfAgencyCommission.getValue().toString()));
					
					if(pkg.getId() == 0){
						//new package
						pkgdb.add(pkg);
					}else{
						//update existing package
						pkgdb.save(pkg);
					}
					getPackages();
					try {
						populateForm(pkg);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					//invalid user input
				}
			}
		});
		btnSave.setBounds(459, 707, 99, 25);
		frame.getContentPane().add(btnSave);
		
		cbPackages = new JComboBox<Package>();
		cbPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(cbPackages.getItemCount() > 0){
						populateForm((Package) cbPackages.getSelectedItem());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbPackages.setBounds(12, 44, 207, 24);
		
		getPackages();
		
		frame.getContentPane().add(cbPackages);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 425, 300, 218);
		frame.getContentPane().add(scrollPane);
		listIncludedProducts = new JList<ProductSupplierLink>(modelIncluded);
		listIncludedProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		        if (arg0.getClickCount() == 2) {
		        	// Double-click detected
		            //remove item
		        	int id = ((ProductSupplierLink)(listIncludedProducts.getSelectedValue())).getId();
		            pkgdb.removeProductSupplier(pkg.getId(), id);
		            //System.out.println("package="+pkg.getId()+" <> prodsup="+id);
		            
		            //refresh list
		            getAllIncludedPS();
		    		getAllNotIncludedPS();
		        }
			}
		});
		scrollPane.setViewportView(listIncludedProducts);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(354, 425, 296, 214);
		frame.getContentPane().add(scrollPane_1);
		
		listNotIncludedProducts = new JList<ProductSupplierLink>(modelNotIncluded);
		listNotIncludedProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
		        	// Double-click detected
		            //remove item
		        	int id = ((ProductSupplierLink)(listNotIncludedProducts.getSelectedValue())).getId();
		            pkgdb.addProductSupplier(pkg.getId(), id);
		            //System.out.println("package="+pkg.getId()+" <> prodsup="+id);
		            
		            //refresh list
		            getAllIncludedPS();
		    		getAllNotIncludedPS();
		        }
			}
		});
		scrollPane_1.setViewportView(listNotIncludedProducts);
		
		JButton btnNewPackage = new JButton("New Package");
		btnNewPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					populateForm(new Package());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewPackage.setBounds(322, 707, 125, 25);
		frame.getContentPane().add(btnNewPackage);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblId_1.setBounds(12, 91, 62, 33);
		frame.getContentPane().add(lblId_1);
	}
	
	private void getPackages(){
		pkgs = (ArrayList<Package>) pkgdb.get();
		cbPackages.removeAllItems();
		for(Package p:pkgs){
			cbPackages.addItem(p);
		}
	}

	private void populateForm(Package p) throws ParseException, Exception {
		pkg = p;
		
		lblId.setText(""+pkg.getId());
		tfName.setText(pkg.getName());
		System.out.println(pkg.getStartDate());
		dcStartDate.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pkg.getStartDate()));
		System.out.println(dcStartDate.getDate());
		dcEndDate.getJCalendar().setMinSelectableDate(dcStartDate.getDate());
		dcEndDate.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pkg.getEndDate()));
		taDescription.setText(pkg.getDescription());
		//tfBasePrice.setText(""+pkg.getBasePrice());
		tfBasePrice.setValue(pkg.getBasePrice());
		//tfAgencyCommission.setText(""+pkg.getAgencyCommission());
		tfAgencyCommission.setValue(pkg.getAgencyCommission());
		
		getAllIncludedPS();
		getAllNotIncludedPS();
	}

	private void getAllIncludedPS() {
		modelIncluded.removeAllElements();
		ArrayList<ProductSupplierLink> pps = new ArrayList<ProductSupplierLink>();
		pps = (ArrayList<ProductSupplierLink>) pkgdb.getProductsSuppliers(pkg.getId());
		Collections.sort(pps);
		for(ProductSupplierLink p: pps){
			modelIncluded.addElement(p);
		}
	}
	private void getAllNotIncludedPS() {
		modelNotIncluded.removeAllElements();
		ArrayList<ProductSupplierLink> pps = new ArrayList<ProductSupplierLink>();
		pps = (ArrayList<ProductSupplierLink>) pkgdb.getProductsSuppliersNot(pkg.getId());
		Collections.sort(pps);
		for(ProductSupplierLink p: pps){
			modelNotIncluded.addElement(p);
		}
	}
}
