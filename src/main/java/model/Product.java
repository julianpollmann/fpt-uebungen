package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Product implements fpt.com.Product, Externalizable {

	private static final long serialVersionUID = 101L;
	private final long id;
	private final SimpleStringProperty name;
	private final SimpleDoubleProperty price;
	private final SimpleIntegerProperty quantity;

//<<<<<<< HEAD
//	public Product(String name, Double price, Integer quantity, long id) {
//=======
	public Product(Long id, String name, Double price, Integer quantity) {
//>>>>>>> origin/master
		this.name = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.quantity = new SimpleIntegerProperty();
		this.id = id;


		setId(id);
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

	@Override	//Hier fehlt noch die direkte Zuweisung
	public void readExternal(ObjectInput extInput) throws IOException, ClassNotFoundException {
		String name = (String) extInput.readObject();
		double price = extInput.readDouble();
		int quantity = extInput.readInt();
		long id = extInput.readLong();

		new Product(id, name, price, quantity);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this);
	}
//=======
////	public long generateId() {
////		return (long)idGen.generateId();
////	}
//>>>>>>> origin/master

}
