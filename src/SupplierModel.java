import java.util.ArrayList;
import java.util.List;


public class SupplierModel {

	List<Supplier> db = new ArrayList<Supplier>();
	
	public void save(Supplier supplier) {
		db.add(supplier);
	}

	public Supplier get(int id) {
		return get().stream().filter(supplier -> supplier.getId() == id ).findFirst().get();
	}

	public void delete(int id) {
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getId() == id){
				db.remove(i);
			}
		}
	}

	public List<Supplier> get() {
		return db;
	}
}
