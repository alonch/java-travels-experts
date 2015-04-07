package products;
import static org.junit.Assert.*;
import org.junit.Test;

import products.Product;


public class ProductTest {

	Product jet= new Product("Jet");;
	Product car=new Product();;

	@Test
	public void createJet() {
		//create product
		//jet = new Product("Jet");
		assertEquals("Jet", jet.getProdName());
		assertNotNull(jet);
	}
	@Test
	public void createCar(){
		//car = new Product();
		assertNotNull(car);
		assertEquals("", car.getProdName());
		
	}
	@Test
	public void editProductAttribute(){
		car.setProdName("new car");
		assertEquals("new car", car.getProdName());
		
		car.setProductId(1);
		assertEquals(1, car.getProductId());
	}

}
