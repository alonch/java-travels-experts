package travelexperts;

import java.util.ArrayList;


public interface PackageModel {
	public void add(Package pkg);
	public void save(Package pkg);
	public Package get(int id);
	public ArrayList<Package> get();
	public ArrayList<Package> getByCustomerId(int customerId);
	public ArrayList<ProductSupplierLink> getProductsSuppliers(int packageId);
	public ArrayList<ProductSupplierLink> getProductsSuppliersNot(int packageId);
	public void addProductSupplier(int packageId, int productSupplierId);
	public void removeProductSupplier(int packageId, int productSupplierId);
	//public void delete(int id);
	//public void reset();
}
