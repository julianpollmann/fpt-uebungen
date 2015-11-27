package model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.ProductList;
import fpt.com.Product; // sollte ProductList nicht model.Product erweitern, statt fpt.com.Product?
//import model.Product;

public class ProductListAlt extends ArrayList<Product> implements ProductList {

	private ArrayList<Product> prodList;

	public ProductListAlt() {
		prodList = new ArrayList<Product>();
	}

	@Override
	public Iterator<Product> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Product e) {
		return prodList.add(e);
	}

	@Override
	public boolean delete(Product product) {
		return prodList.remove(product);
	}

	@Override
	public int size() {
		return prodList.size();
	}

	@Override
	public Product findProductById(long id) {
		for (Product prod : prodList) {
			if (prod.getId() == id) {
				return prod;
			}
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for(Product prod : prodList) {
			if(prod.getName().equals(name)) {
				return prod;
			}
		}
		return null;
	}

}
