package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Product implements fpt.com.Product {

	private long id;
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;
	private SimpleIntegerProperty quantity;

	public Product(String name, Double price, Integer quantity) {
		this.name = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.quantity = new SimpleIntegerProperty();

//		setId(0); TODO: ID Generator oder sowas?!
		setName(name);
		setPrice(price);
		setQuantity(quantity);
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
		return null;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		return null;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		return null;
	}

}
