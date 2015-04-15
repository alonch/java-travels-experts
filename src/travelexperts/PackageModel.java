package travelexperts;

import java.util.List;


public interface PackageModel {
	public void add(Package pkg);
	public void save(Package pkg);
	public Package get(int id);
	public List<Package> get();
	public List<PackageProductSupplierLink> getProductsSuppliers(int packageId);
	public void addProductSupplier(int packageId, int productSupplierId);
	public void removeProductSupplier(int packageId, int productSupplierId);
	//public void delete(int id);
	//public void reset();
}
