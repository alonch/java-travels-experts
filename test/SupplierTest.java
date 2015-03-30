import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SupplierTest {

	Supplier supplier;
	
	@Before
	public void setUp() throws Exception {
		supplier = new Supplier();
	}

	@Test
	public void testName() {
		supplier.setName("AirCanada");
		assertEquals("AirCanada", supplier.getName());
	}
	
	@Test
	public void testId() {
		supplier.setId(1);
		assertEquals(1, supplier.getId());
	}

}
