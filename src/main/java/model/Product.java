package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Serializable;

public class Product implements fpt.com.Product, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final long id;
	private final SimpleStringProperty name;
	private final SimpleDoubleProperty price;
	private final SimpleIntegerProperty quantity;

	public Product(String name, Double price, Integer quantity, long id) {
		this.name = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.quantity = new SimpleIntegerProperty();
		this.id = id;

		setName(name);
		setPrice(price);
		setQuantity(quantity);
		setId(id);
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setId(long id) {
//		this.id = id;
	}

	@Override
	public double getPrice() {
		return this.price.get();
	}

	@Override
	public void setPrice(double price) {
		this.price.set(price);
	}

	@Override
	public int getQuantity() {
		return this.quantity.get();
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	@Override
	public String getName() {
		return this.name.get();
	}

	@Override
	public void setName(String name) {
		this.name.set(name);
	}

	@Override
	public ObservableValue<String> nameProperty() {
		return this.name;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		return this.price;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		return this.quantity;
	}

}
