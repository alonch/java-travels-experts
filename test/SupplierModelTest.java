import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class SupplierModelTest {

	private ItemModel model;
	private Supplier airCanada;
	private Supplier westjet;

	@Before
	public void setUp() throws Exception {
		model = new SupplierModelDB();

		airCanada = new Supplier();
		airCanada.setId(10);
		airCanada.setName("AirCanada");
		model.save(airCanada);

		westjet = new Supplier();
		westjet.setName("WestJet");
		westjet.setId(11);
		model.save(westjet);
	}

	@Test
	public void addTest() {
		Supplier savedSupplier = model.get(airCanada.getId());
		assertEquals(airCanada.getName(), savedSupplier.getName());
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteTest() {
		model.delete(airCanada.getId());
		
		model.get(airCanada.getId()); //will raise NoSuchElementException
	}
	
	@Test
	public void deleteIsolateTest() {
		model.delete(airCanada.getId());
		model.get(westjet.getId());// Not exception
	}
	
	

	@Test
	public void getAllSuppliersTest(){
		List<Supplier> all = model.get();
		assertEquals(2, all.size());
		assertTrue(all.get(0).getId() == westjet.getId() || all.get(0).getId() == airCanada.getId());
	}
	@After
	public void tearDown(){
		model.reset();
	}
}
