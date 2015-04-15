package travelexperts;


public class ProductSupplierLink implements Comparable<ProductSupplierLink>{
	private int id;
	private int productId;
	private int supplierId;
	private String productName;
	private String supplierName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		
		this.productId = productId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		
		this.supplierId = supplierId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String toString(){
		return productName+" >> "+supplierName;
	}
	@Override
	public int compareTo(ProductSupplierLink o) {
		return toString().compareTo(o.toString());
	}
}
