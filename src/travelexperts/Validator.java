package travelexperts;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public static boolean validPostalCode(JTextField checkMe){
		String regex = "^([A-Za-z].[0-9].){3}$"; //A1B2C3
		String regex2 = "^[A-Za-z].[0-9].[A-Za-z].[ ].[0-9].[A-Za-z].[0-9].$"; //A1B 2C3
		
		Pattern pattern = Pattern.compile(regex);
		Pattern pattern2= Pattern.compile(regex2);
		
		Matcher matcher = pattern.matcher((checkMe.getText()).trim());
		Matcher matcher2 = pattern.matcher((checkMe.getText()).trim());
		
	    
	    if(matcher.matches() || matcher2.matches()){
	    	return true;
	    }
	    JOptionPane.showMessageDialog(null, 
				"Error: Invalid PostalCode\n"
				+ "Valid Formats are A1A1A1 or A1A 1A1", 
				"Error", JOptionPane.ERROR_MESSAGE);
		checkMe.requestFocus();
	    return false;
	}
	public static boolean validPhoneNumber(JTextField checkMe){
		String regex = "^[(].[0-9]{3}[)].[0-9]{3}[-].[0-9]{4}$"; //(403) 210-7833
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher((checkMe.getText()).trim());
		
	    
	    if(matcher.matches()){
	    	return true;
	    }
	    JOptionPane.showMessageDialog(null, 
				"Error: Invalid Phone Number\n"
				+ "Valid Format is (123)123-1234", 
				"Error", JOptionPane.ERROR_MESSAGE);
		checkMe.requestFocus();
	    return false;
	}
	

}
