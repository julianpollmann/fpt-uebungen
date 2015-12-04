package model;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product; // muss das nicht von model.Product erben?
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Order extends ArrayList<Product> implements fpt.com.Order {

	private int quantity;
	private double sum;

//	UNNOETIG, da ueber Superklasse vererbt

//	private ArrayList<Product> produktListe;
//
//	@Override
//	public Iterator<Product> iterator() {
//		return this.iterator();
//	}
//
//	@Override
//	public boolean add(Product p) {
//		boolean works = this.add(p);
//		if (works) {
//			this.quantity++;
//			this.sum += p.getPrice();
//		}
//		return works;
//	}
//
	@Override
	public boolean delete(Product p) {
		boolean works = this.remove(p);
		if (works) {
			this.quantity--;
			this.sum -= p.getPrice();
		}
		return works;
	}
//
//	@Override
//	public int size() {
//		return this.size();
//	}

	@Override
	public Product findProductById(long id) {
		for (Product p : this) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (Product p : this) {
			if (p.getName() == name) {
				return p;
			}
		}
		return null;
	}

	@Override
	public double getSum() {
		return sum;
	}

	@Override
	public int getQuantity() {
		return	quantity;
	}

}
