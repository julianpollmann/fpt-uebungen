package model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;

public class ProductList extends ArrayList<Product> implements fpt.com.ProductList {

	@Override
	public boolean delete(Product product) {
		// TODO Auto-generated method stub
		return this.remove(product);
	}

	@Override
	public Product findProductById(long id) {
		for (Product prod : this) {

			if (prod.getId() == id) {

				return prod;

			}

		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (Product prod : this) {

			if (prod.getName().equals(name)) {

				return prod;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
