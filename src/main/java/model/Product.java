package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Product implements fpt.com.Product {

	private long id;
	private final SimpleStringProperty name;
	private final SimpleDoubleProperty price;
	private final SimpleIntegerProperty quantity;

	public Product(Long id, String name, Double price, Integer quantity) {
		this.name = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.quantity = new SimpleIntegerProperty();

		setId(id);
		setName(name);
		setPrice(price);
		setQuantity(quantity);

		System.out.println("++++++++++++++++++++++++");
		System.out.println(id);
		System.out.println(name);
		System.out.println(price);
		System.out.println(quantity);
		System.out.println("++++++++++++++++++++++++");

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

//	public long generateId() {
//		return (long)idGen.generateId();
//	}

}
