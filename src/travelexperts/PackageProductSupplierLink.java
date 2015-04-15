package travelexperts;


public class PackageProductSupplierLink {
	private int packageId;
	private int productSupplierId;
	private String ProdName;
	private String SupName;
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getProductSupplierId() {
		return productSupplierId;
	}
	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}
	public String toString(){
		
		return ""+ProdName+" >> "+SupName;
	}
	public String getProdName() {
		return ProdName;
	}
	public void setProdName(String prodName) {
		ProdName = prodName;
	}
	public String getSupName() {
		return SupName;
	}
	public void setSupName(String supName) {
		SupName = supName;
	}
}
