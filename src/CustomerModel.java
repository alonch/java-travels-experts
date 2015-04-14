import java.util.List;


public interface CustomerModel {
	//public Customer get(int id);
	public void save(Customer customer);
	public List<Customer> get(int agentId);
	//public void add(Customer customer);
	//public void delete(int id);
	//public void reset();
	
}
