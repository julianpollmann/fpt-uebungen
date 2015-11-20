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


	@Override
	public long getId() {
		return this.id;
		// TODO Auto-generated method stub
	}

	@Override
	public void setId(long id) {
		this.id = id;
		// TODO Auto-generated method stub
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price.get();
	}

	@Override
	public void setPrice(double price) {
		// TODO Auto-generated method stub
		this.price.set(price);
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return this.quantity.get();
	}

	@Override
	public void setQuantity(int quantity) {
		// TODO Auto-generated method stub
		this.quantity.set(quantity);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name.get();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name.set(name);
	}

	@Override
	public ObservableValue<String> nameProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
