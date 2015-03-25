
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
		
		//edit product attribute
		p2.ProdName("Ship");
		p2.ProductId(1);
		
		//show product attributes
		p1.toString();
		p2.toString();
	}

}
