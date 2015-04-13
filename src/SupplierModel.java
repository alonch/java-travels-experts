import java.util.ArrayList;
import java.util.List;


public interface SupplierModel {
	public void save(Supplier supplier);
	public Supplier get(int id);
	public void delete(int id);
	public List<Supplier> get();
	public void reset();
}
