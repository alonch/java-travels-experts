package travelexperts;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PackageGUI {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfBasePrice;
	private JTextField tfAgencyCommission;
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
		lblId.setBounds(12, 93, 73, 33);
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
		dcStartDate.setBounds(152, 183, 154, 22);
		frame.getContentPane().add(dcStartDate);
		
		dcEndDate = new JDateChooser();
		dcEndDate.setBounds(152, 221, 154, 22);
		frame.getContentPane().add(dcEndDate);
		
		taDescription = new JTextArea();
		taDescription.setBounds(152, 263, 521, 40);
		frame.getContentPane().add(taDescription);
		
		tfBasePrice = new JTextField();
		tfBasePrice.setBounds(557, 150, 116, 22);
		frame.getContentPane().add(tfBasePrice);
		tfBasePrice.setColumns(10);
		
		tfAgencyCommission = new JTextField();
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
		
		JLabel lblClickAProduct = new JLabel("click a product to remove");
		lblClickAProduct.setBounds(31, 375, 171, 16);
		frame.getContentPane().add(lblClickAProduct);
		
		JLabel lblClickAProduct_1 = new JLabel("click a product to add");
		lblClickAProduct_1.setBounds(354, 375, 171, 16);
		frame.getContentPane().add(lblClickAProduct_1);
		
		modelNotIncluded = new DefaultListModel<ProductSupplierLink>();
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(574, 707, 99, 25);
		frame.getContentPane().add(btnExit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(459, 707, 99, 25);
		frame.getContentPane().add(btnSave);
		
		cbPackages = new JComboBox<Package>();
		cbPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populateForm();
			}
		});
		cbPackages.setBounds(12, 44, 207, 24);
		
		getPackages();
		for(Package p:pkgs){
			cbPackages.addItem(p);
		}
		
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
	}
	
	private void getPackages(){
		pkgs = (ArrayList<Package>) pkgdb.get();
	}

	private void populateForm() {
		pkg = (Package) cbPackages.getSelectedItem();
		
		lblId.setText(""+pkg.getId());
		tfName.setText(pkg.getName());
		dcStartDate.setDate(pkg.getStartDate());
		dcEndDate.setDate(pkg.getEndDate());
		taDescription.setText(pkg.getDescription());
		tfBasePrice.setText(""+pkg.getBasePrice());
		tfAgencyCommission.setText(""+pkg.getAgencyCommission());
		
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
