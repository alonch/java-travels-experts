import java.util.Vector;

public class Tester {
	
	private static Vector<Product> productList;

	public static void main(String[] args) {
		
		//Product p = new Product();
		//p.setProductId(999);
		//p.setProdName("New Product");
		//ProductsDB.AddProduct(p);
				
		//Product p = ProductsDB.GetProductByID(11);
		//p.setProdName("New Super Product");
		//ProductsDB.UpdateProduct(p);
		
		productList = ProductsDB.GetProducts();
		for (int i=0; i < productList.size(); i++) {
			System.out.print(productList.get(i).getProductId().toString() + "\t");
			System.out.print(productList.get(i).getProdName() + "\t");			
			System.out.println();
		}
		
		//Product p = productList.get(8);
		//p.setProdName("CSD");
		//ProductDB.UpdateProduct(p);

	}

}
