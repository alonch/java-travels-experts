
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
		
		assertEquals("Jet", p1.ProdName());
		assertNotNull(p1);
		
		p2 = new Product();
		assertNotNull(p2);
		assertEquals("", p2.ProdName());
		
		//edit and retrieve attributes
		p2.ProdName("Jet");
		assertEquals("Jet", p2.ProdName());
		
		p2.ProductId(1);
		assertEquals(1, p2.ProductId());
		
	}

}
