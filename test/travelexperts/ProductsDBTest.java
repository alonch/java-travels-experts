package travelexperts;

import travelexperts.Item;
import travelexperts.ProductModelDB;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;


public class ProductsDBTest {
	ArrayList<Item> products;
	Item product;
	ProductModelDB productDB;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void testGetProducts() {
		products = null;
		productDB = new ProductModelDB();
		products = (ArrayList<Item>) productDB.get();
		assertNotNull("get all products", products);
	}
	@Test
	public void testGetProductById(){
		product=null;
		productDB = new ProductModelDB();
		product = productDB.get(1);
		assertNotNull("get a product", product);
	}
}
