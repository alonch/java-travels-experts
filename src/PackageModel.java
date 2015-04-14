import java.util.List;


public interface PackageModel {
	public void add(Package pkg);
	public void save(Package pkg);
	public Package get(int id);
	public List<Package> get();
	//public void delete(int id);
	//public void reset();
}
