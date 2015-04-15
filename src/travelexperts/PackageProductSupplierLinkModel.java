package travelexperts;


public interface PackageProductSupplierLinkModel {
	public boolean link(int packageId, int productSupplierId);
	public boolean unlink(int packageId, int productSupplierId);
}
