package model;

import fpt.com.Product;
import javafx.collections.ModifiableObservableListBase;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> implements fpt.com.ProductList {

	public ProductList produktliste = new ProductList();

	@Override
	protected void doAdd(int index, Product p) {
		this.produktliste.add(index, p);
	}

	@Override
	protected Product doRemove(int index) {
		return produktliste.remove(index);
	}

	@Override
	protected Product doSet(int index, Product p) {
		return produktliste.set(index, p);
	}

	@Override
	public Product get(int index) {
		return produktliste.get(index);
	}

	@Override
	public int size() {
		return produktliste.size();
	}

	@Override
	public boolean delete(Product p) {
		return produktliste.delete(p);
	}

	@Override
	public Product findProductById(long id) {
		return produktliste.findProductById(id);
	}

	@Override
	public Product findProductByName(String name) {
		return produktliste.findProductByName(name);
	}

}
