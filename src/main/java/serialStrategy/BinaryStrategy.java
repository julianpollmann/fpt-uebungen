package serialStrategy;

import java.io.*;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class BinaryStrategy implements SerializableStrategy {

	@Override
	public Product readObject() throws IOException {
		Product readProduct = null;
		try (FileInputStream fi = new FileInputStream("ProdukteBinaer.ser");
				ObjectInputStream is = new ObjectInputStream(fi))
		{
			readProduct = (Product) is.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return readProduct;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		try (FileOutputStream fs = new FileOutputStream("ProdukteBinaer.ser");
				ObjectOutputStream os = new ObjectOutputStream(fs))
		{
			os.writeObject(obj);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub

	}

}
