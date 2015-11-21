package model;

import fpt.com.Product;
import javafx.collections.ModifiableObservableListBase;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> implements fpt.com.ProductList {

	private ProductList produktliste = new ProductList();

	@Override
	protected void doAdd(int arg0, Product arg1) {
		// TODO Auto-generated method stub
		this.produktliste.add(arg0, arg1);
	}

	@Override
	protected Product doRemove(int arg0) {
		// TODO Auto-generated method stub
		return produktliste.remove(arg0);
	}

	@Override
	protected Product doSet(int arg0, Product arg1) {
		// TODO Auto-generated method stub
		return produktliste.set(arg0, arg1);
	}

	@Override
	public Product get(int arg0) {
		// TODO Auto-generated method stub
		return produktliste.get(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return produktliste.size();
	}

	@Override
	public boolean delete(Product product) {
		return produktliste.delete(product);
	}

	@Override
	public Product findProductById(long id) {
		return produktliste.findProductById(id);
		// TODO Auto-generated method stub
	}

	@Override
	public Product findProductByName(String name) {
		return produktliste.findProductByName(name);
		// TODO Auto-generated method stub
	}

}
