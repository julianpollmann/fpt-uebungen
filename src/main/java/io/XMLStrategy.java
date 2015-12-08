package io;

import java.io.*;
import java.beans.*;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class XMLStrategy implements SerializableStrategy {


	@Override
	public Product readObject() throws IOException {
		Product readProduct = null;

		try (FileInputStream fi = new FileInputStream("products.xml");
				XMLDecoder decoder = new XMLDecoder(fi)) {
			readProduct = (Product) decoder.readObject(); // Read Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readProduct;
	}


	@Override
	public void writeObject(Product obj) throws IOException {
		try (FileOutputStream fo = new FileOutputStream("products.xml");
				XMLEncoder encoder = new XMLEncoder(fo)) {
			encoder.writeObject(obj); // write Object
			encoder.flush();
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

}
