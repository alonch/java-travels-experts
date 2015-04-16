package travelexperts;


import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Validator {
	public static boolean notEmpty(JTextField checkMe){
		
		if(checkMe.getText() == null){
			//null value
			JOptionPane.showMessageDialog(null, 
					"Error: Field must not be left blank", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		if(((checkMe.getText()).trim()).length() < 1){
			//empty string
			JOptionPane.showMessageDialog(null, 
					"Error: Field must not be left blank", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		return true;
	}
	public static boolean notEmpty(JTextArea checkMe){
		
		if(checkMe.getText() == null){
			//null value
			JOptionPane.showMessageDialog(null, 
					"Error: Field must not be left blank", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		if(((checkMe.getText()).trim()).length() < 1){
			//empty string
			JOptionPane.showMessageDialog(null, 
					"Error: Field must not be left blank", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		return true;
	}
	public static boolean validDate(JDateChooser checkMe){
		if(checkMe.getDate() == null){
			//invalid date
			JOptionPane.showMessageDialog(null, 
					"Error: Invalid date", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		return true;
	}
	public static boolean dateInRange(JDateChooser checkMe, JDateChooser startDate){
		if((checkMe.getDate()).after(startDate.getDate())){
			return true;
		}
		//end date comes before start date
		JOptionPane.showMessageDialog(null, 
				"Error: Choose a date after "+startDate.getDate(), 
				"Error", JOptionPane.ERROR_MESSAGE);
		checkMe.requestFocus();
		return false;
	}
	public static boolean numberIsDouble(JFormattedTextField checkMe){
		try{
			Double.parseDouble(checkMe.getValue().toString());
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, 
					"Error: Invalid input", 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		return true;
	}
	public static boolean doubleIsLessthan(JFormattedTextField checkMe, double minValue, double maxValue){
		if(Double.parseDouble(checkMe.getValue().toString()) >= maxValue ||
				Double.parseDouble(checkMe.getValue().toString()) < minValue){
			JOptionPane.showMessageDialog(null, 
					"Error: Use a number between "+minValue+" and "+maxValue, 
					"Error", JOptionPane.ERROR_MESSAGE);
			checkMe.requestFocus();
			return false;
		}
		return true;
	}
}
