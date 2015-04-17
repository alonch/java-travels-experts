package travelexperts;

public class Agency {
	private int Id;
	private String address;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	private String phone;
	private String fax;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String toString() {
		return "" + address + " " + city;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Agency)) {
			return false;
		}
		Agency instance = (Agency) obj;
		return instance.getId() == getId();
	}

}
