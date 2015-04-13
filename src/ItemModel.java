//import java.util.ArrayList;
import java.util.List;


public interface ItemModel {
	public void save(Item supplier);
	public Item get(int id);
	public void delete(int id);
	public List<Item> get();
	public void reset();
	void add(Item product);
}
