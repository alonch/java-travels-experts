

public class Product {

	private int ProductId;
	private String ProdName="";
	
	public Product(String name) {
		this.ProdName=name;
	}
	public Product() {
	}
	
	public void setProdName(String name) {
		this.ProdName=name;
	}
	public void setProductId(int id) {
		this.ProductId=id;
	}
	public String getProdName() {
		return this.ProdName;
	}
	public int getProductId() {
		return this.ProductId;
	}
	public String toString(){
		return ""+this.ProductId+
				" "+this.ProdName;
	}

}
