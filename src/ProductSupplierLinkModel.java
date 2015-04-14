
public interface ProductSupplierLinkModel {
	public boolean link(int productId, int supplierId);
	public boolean unlink(int productId, int supplierId);
}
