package travelexperts;
import java.util.List;


public interface CustomerModel {
	//public Customer get(int id);
	public void save(Customer customer);
	public List<Customer> get(int agentId);
	public List<Customer> get();
        public Customer getByUserIdAndPassword(String userId, String password) throws Exception;
	//public void add(Customer customer);
	//public void delete(int id);
	//public void reset();
	
}
