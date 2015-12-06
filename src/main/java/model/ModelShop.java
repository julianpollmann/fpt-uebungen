package model;

import java.util.List;

import fpt.com.Product;
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> implements fpt.com.ProductList {

	// TODO: Irgendwie muss produktListe noch mit der productList verknüpft werden
	private ProductList produktliste;
	private IDGenerator idGen;

	public ModelShop() {
		produktliste = new ProductList();
		idGen = new IDGenerator();
	}

	@Override
	protected void doAdd(int index, Product p) {
		produktliste.add(index, p);
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

	public ProductList getProduktliste() {
		return produktliste;
	}

	public void setProduktliste(ProductList produktliste) {
		this.produktliste = produktliste;
	}

	public ProductList getProductList() {
		return produktliste;
	}

	public long setId() {
		return (long)idGen.generateId();
	}
}
