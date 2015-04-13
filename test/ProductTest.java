import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	Product car;

	@Before
	public void setUp() throws Exception {
		car=new Product();;
	}
	
	@Test
	public void createJet() {
		//create product
		//jet = new Product("Jet");
		assertNotNull(car);
	}
	@Test
	public void editProductAttribute(){
		car.setName("new car");
		assertEquals("new car", car.getName());
		
		car.setId(1);
		assertEquals(1, car.getId());
	}

}
