package travelexperts;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Package {
	private int Id=0;
	private String name;
	private String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private String description;
	private double basePrice;
	private double agencyCommission;
	private boolean deleted;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endDate);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getAgencyCommission() {
		return agencyCommission;
	}
	public void setAgencyCommission(double agencyCommission) {
		this.agencyCommission = agencyCommission;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String toString(){
		return name;
	}
}
