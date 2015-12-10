package io;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fpt.com.Product;
import fpt.com.ProductList;
import fpt.com.SerializableStrategy;

public class XStreamStrategy implements SerializableStrategy {

	private XStream xstream;
	private ProductList productList;
	private Product product;

	public XStreamStrategy() {
//		xstream = new XStream(new DomDriver());
//		xstream.alias("product", Product.class);
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

//		this.product = obj;
//		System.out.println(product.getName());
		xstream.aliasField("waren", Product.class, "size");


		try (FileWriter fw = new FileWriter("produktliste.xml")){
			xstream.toXML(obj, fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		createXStream(model.Product.class);
		
	}

	@Override
	public XStream createXStream(Class<? extends Product> clazz) {
		return SerializableStrategy.super.createXStream(clazz);
	}

//	private String formatXml() {
//		return String token;
//	}

}
