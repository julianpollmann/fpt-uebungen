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

	public XStreamStrategy() {
		xstream = new XStream(new DomDriver());
	}

	@Override
	public Product readObject() throws IOException {

		try(FileReader fr = new FileReader("produktliste.xml")) {
			readObject = xstream.fromXML(fr);
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {

		try (FileWriter fw = new FileWriter("produktliste.xml")){
			xstream.toXML(p1, fw);
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
		// TODO Auto-generated method stub

	}

	@Override
	public XStream createXStream(Class<? extends Product> clazz) {
		// TODO Auto-generated method stub
		return SerializableStrategy.super.createXStream(clazz);
	}

}
