
public class Product {

	private int ProductId;
	private String ProdName="";
	
	public Product(String name) {
		this.ProdName=name;
	}

	public Product() {
		
	}

	public void ProdName(String name) {
		this.ProdName=name;
	}

	public void ProductId(int id) {
		this.ProductId=id;
	}

	public String ProdName() {
		return this.ProdName;
	}
	public int ProductId() {
		return this.ProductId;
	}
	
	public String toString(){
		return ""+this.ProductId+
				" "+this.ProdName;
	}

}
