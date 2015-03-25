
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProductTest {

	Product p1;
	Product p2;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//create product
		p1 = new Product("Jet");
		p2 = new Product();
		
		assertEquals("Jet", p1.ProdName());
		
		//edit product attribute
		p2.ProdName("Jet");
		p2.ProductId(1);
		
		assertEquals("Jet", p2.ProdName());
		assertEquals(1, p2.ProductId());
		
		//get product attributes
		p1.ProductId();
		p1.ProdName();
		
				
		//show product
		p1.toString();
		p2.toString();
	}

}
