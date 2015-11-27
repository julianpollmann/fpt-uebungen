package model;

import javafx.collections.ModifiableObservableListBase;

public class ModelShopAlt extends ModifiableObservableListBase<Product> {

	private ProductListAlt prodList;

	public ModelShopAlt() {
		prodList = new ProductListAlt();
	}

	@Override
	public Product get(int index) {
		this.prodList.findProductById(index);
		return null;
	}

	@Override
	protected void doAdd(int index, Product element) {
		this.prodList.add(element);
	}

	@Override
	protected Product doSet(int index, Product element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Product doRemove(int index) {
		this.prodList.remove(index);
		return null;
	}

	@Override
	public int size() {
		this.prodList.size();
		return 0;
	}

	public ProductListAlt getAll() {
		return prodList;
	}

}
