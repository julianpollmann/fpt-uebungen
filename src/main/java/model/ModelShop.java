package model;

import java.util.List;

import fpt.com.Product;
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> implements fpt.com.ProductList {

	// TODO: Irgendwie muss productList noch mit der productList verknüpft werden
	private ProductList productList;


	public ModelShop() {
		productList = new ProductList();

	}


	@Override
	protected void doAdd(int index, Product p) {
		productList.add(index, p);
	}

	@Override
	protected Product doRemove(int index) {
		return productList.remove(index);
	}

	@Override
	protected Product doSet(int index, Product p) {
		return productList.set(index, p);
	}

	@Override
	public Product get(int index) {
		return productList.get(index);
	}

	@Override
	public int size() {
		return productList.size();
	}

	@Override
	public boolean delete(Product p) {
		return productList.delete(p);
	}

	@Override
	public Product findProductById(long id) {
		return productList.findProductById(id);
	}

	@Override
	public Product findProductByName(String name) {
		return productList.findProductByName(name);
	}

	public ProductList getProductList() {
		return productList;
	}

	public void setProductList(ProductList productList) {
		this.productList = productList;
	}

}
