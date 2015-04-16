package travelexperts;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Main extends JFrame {
	public Main() {
		
		getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btnPackages = new JButton("Packages");
		btnPackages.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PackageGUI window = new PackageGUI();
				window.frame.setVisible(true);
			}
		});
		getContentPane().add(btnPackages);

		JButton btnAgents = new JButton("Agents");
		btnAgents.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnAgents.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIAgentsTx().setVisible(true);
			}
		});
		getContentPane().add(btnAgents);

		JButton btnProducts = new JButton("Products");
		btnProducts.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnProducts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ItemsUI(true).setVisible(true);
			}
		});
		getContentPane().add(btnProducts);

		JButton btnSuppliers = new JButton("Suppliers");
		btnSuppliers.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnSuppliers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ItemsUI(false).setVisible(true);
			}
		});
		getContentPane().add(btnSuppliers);

		setSize(600, 800);
		setResizable(false);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}

}
