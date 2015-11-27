package model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product; // muss das nicht von model.Product erben?
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Order extends ArrayList<Product> implements fpt.com.Order {

	private int quantity;
	private double sum;
	private ArrayList<Product> produktliste;

	@Override
	public Iterator<Product> iterator() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Product e) {

		boolean works = this.add(e);

		if (works) {

			this.quantity++;
			this.sum += e.getPrice();

		}

		return works;
	}


	@Override
	public boolean delete(Product p) {
		// TODO Auto-generated method stub
		boolean works = this.remove(p);

		if (works) {

			this.quantity--;
			this.sum -= p.getPrice();
		}

		return works;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product findProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getSum() {
		return sum;
		// TODO Auto-generated method stub
	}

	@Override
	public int getQuantity() {
		return	quantity;
		// TODO Auto-generated method stub
	}

}
