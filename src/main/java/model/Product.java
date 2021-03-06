package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.persistence.*;

import org.apache.openjpa.persistence.Persistent;
import org.apache.openjpa.persistence.jdbc.Strategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@Entity()
@Table(name="products")
@XStreamAliasType ("Ware")  // OpenJPA Ableitungen werden als Ware bezeichnet.
@XStreamAlias("Ware")
public class Product implements fpt.com.Product, Externalizable {

	private static final long serialVersionUID = 101L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "products_SEQ")
	@XStreamConverter(io.IdConverter.class)
	private long id;

	@Persistent
    @Strategy("fpt.com.db.StringPropertyValueHandler")
	@XStreamConverter(io.NameConverter.class)
	private SimpleStringProperty name;

	@Persistent
    @Strategy("fpt.com.db.DoublePropertyValueHandler")
	@XStreamConverter(io.PriceConverter.class)
	private SimpleDoubleProperty price;

	@Persistent
    @Strategy("fpt.com.db.IntegerPropertyValueHandler")
	@XStreamConverter(io.QuantityConverter.class)
	private SimpleIntegerProperty quantity;

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

	public Product(String name, Double price, Integer quantity) {
		this.name = new SimpleStringProperty();
		this.price = new SimpleDoubleProperty();
		this.quantity = new SimpleIntegerProperty();
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

	@Override
	public void readExternal(ObjectInput extInput) throws IOException, ClassNotFoundException {
		this.setId(extInput.readLong());
		this.setName((String) extInput.readObject());
		this.setPrice(extInput.readDouble());
		this.setQuantity(extInput.readInt());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(this.getId());
		out.writeObject(this.getName());
		out.writeDouble(this.getPrice());
		out.writeInt(this.getQuantity());
	}

}
