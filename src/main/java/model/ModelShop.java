package model;

import fpt.com.Product;
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> implements fpt.com.ProductList {

	// TODO: Irgendwie muss produktListe noch mit der productList verknüpft werden
	public ProductList produktliste;
	private ObservableList<Product> productList = FXCollections.observableArrayList();

	public ModelShop() {
		produktliste = new ProductList();

		// Beispielprodukte erzeugen
		model.Product p1 = new model.Product("TestProdukt", 9.00, 2);
		model.Product p2 = new model.Product("TestProdukt 2", 10.00, 2);
		doAdd(0, p1);
		doAdd(1, p2);
		productList.addAll(p1, p2);
	}

	// Produktliste an Controller übergeben
	public ObservableList<Product> getProductList() {
		return productList;
	}

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
