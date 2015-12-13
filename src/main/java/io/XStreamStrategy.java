package io;

import java.io.*;

import com.thoughtworks.xstream.*;

import fpt.com.Product;
import fpt.com.ProductList;
import fpt.com.SerializableStrategy;

public class XStreamStrategy implements SerializableStrategy {

	private XStream xstream;
	private ProductList productList;
	private Product product;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OutputStreamWriter osw;
	private OutputStream os;

	public XStreamStrategy() {
		Product product = new model.Product();
	}

	@Override
	public Product readObject() throws IOException {

		try(FileReader fr = new FileReader("produktliste.xml")) {
			product = (Product) xstream.fromXML(fr);
			return product;
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {

		System.out.println("++++++++++++++++++++++++++++");
		System.out.println(obj.getName());

		this.product = (model.Product) obj;

		System.out.println(this.product.getId());
		System.out.println(this.product.getName());
		System.out.println(this.product.getPrice());
		System.out.println(this.product.getQuantity());
		System.out.println("-----------------------------");

		xstream.registerConverter(new IdConverter());
		xstream.registerConverter(new NameConverter());
		xstream.registerConverter(new PriceConverter());
		xstream.registerConverter(new QuantityConverter());

		xstream.alias("ware", model.Product.class);

		xstream.toXML(this.product, this.os);

		System.out.println("-----------------------------");

	}

	@Override
	public void close() throws IOException {
		this.oos.close();
		this.os.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		xstream = createXStream(model.Product.class);


		if(input != null) {
			this.ois = xstream.createObjectInputStream(input);
		}
		if(output != null) {
			this.oos = xstream.createObjectOutputStream(output);
			this.os = output;
		}

	}

	@Override
	public XStream createXStream(Class<? extends Product> clazz) {
		return SerializableStrategy.super.createXStream(clazz);
	}
}
