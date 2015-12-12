package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.thoughtworks.xstream.annotations.XStreamConverter;

public class Product implements fpt.com.Product, Externalizable {

	private static final long serialVersionUID = 101L;
	private final long id;

	@XStreamConverter(io.NameConverter.class)
	private final SimpleStringProperty name;

	@XStreamConverter(io.PriceConverter.class)
	private final SimpleDoubleProperty price;

	@XStreamConverter(io.QuantityConverter.class)
	private final SimpleIntegerProperty quantity;

	public Product() {

		id = 0;
		name = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
		quantity = new SimpleIntegerProperty();
	}

	public Product(Long id, String name, Double price, Integer quantity) {
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

	@Override	//Hier fehlt noch die direkte Zuweisung der Daten
	public void readExternal(ObjectInput extInput) throws IOException, ClassNotFoundException {
		Product temp = (Product) extInput.readObject();

		System.out.println(temp.getName());

//		this.setName(temp.getName());
//		this.setPrice(temp.getPrice());
//		this.setQuantity(temp.getQuantity());
//		this.setId(temp.getId());

	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this);
	}

}
