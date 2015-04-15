package travelexperts;


public class Person {
	private int Id;
	private String firstName;
	private String lastName;
	private String busPhone;
	private String email;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBusPhone() {
		return busPhone;
	}
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
