import java.util.ArrayList;
import java.util.List;


public class SupplierModelMemory implements ItemModel{

	List<Item> db = new ArrayList<Item>();
	
	@Override
	public void add(Item product) {
		// TODO Auto-generated method stub
		
	}
	
	public void save(Item supplier) {
		db.add(supplier);
	}

	public Item get(int id) {
		return get().stream().filter(supplier -> supplier.getId() == id ).findFirst().get();
	}

	public void delete(int id) {
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getId() == id){
				db.remove(i);
			}
		}
	}

	public List<Item> get() {
		return db;
	}

	@Override
	public void reset() {
		db = new ArrayList<Item>();
	}
	
}
