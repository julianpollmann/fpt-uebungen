package io;

import java.io.*;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class BinaryStrategy implements SerializableStrategy {

	ObjectInputStream ois;
	ObjectOutputStream oos;

	@Override
	public Product readObject() throws IOException {
		Product readProduct = null;
		System.out.println("Test1231");

			try {
				readProduct = (Product) ois.readObject();
				System.out.println("Test");
				System.out.println(readProduct.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return readProduct;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
			System.out.println(obj.getName());
			oos.writeObject(obj);
	}

	@Override
	public void close() throws IOException {
		 oos.flush();
		 oos.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		oos = new ObjectOutputStream(output);
		ois = new ObjectInputStream(input);
	}

}
