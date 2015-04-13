import java.util.List;


public interface ProductModel {
	public void add(Product product);
	public void save(Product product);
	public Product get(int id);
	public void delete(int id);
	public List<Product> get();
	public void reset();
}
