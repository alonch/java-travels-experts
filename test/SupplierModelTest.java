import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class SupplierModelTest {

	private SupplierModel model;
	private Supplier airCanada;
	private Supplier westjet;

	@Before
	public void setUp() throws Exception {
		model = new SupplierModel();

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
	public void getAllSuppliersTest(){
		List<Supplier> all = model.get();
		assertEquals(2, all.size());
		assertTrue(all.contains(westjet));
		assertTrue(all.contains(airCanada));
	}
}
