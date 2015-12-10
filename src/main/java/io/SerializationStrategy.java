package io;

import java.io.IOException;

import fpt.com.Product;
import fpt.com.ProductList;
import fpt.com.SerializableStrategy;


public class SerializationStrategy {

	private SerializableStrategy strategy;
	private ProductList productList;
	private Product product;

	/*
	 * Basisstrategieklasse
	 * Strategie wird als Parameter übergeben
	 */
	public SerializationStrategy(SerializableStrategy strategy) {
		this.strategy = strategy;
	}

	/*
	 * Ändere Strategie, z.B. zur Laufzeit
	 */
	public void setStrategy(SerializableStrategy strategy) {
		this.strategy = strategy;
	}

	public SerializableStrategy getStrategy() {
		return strategy;
	}

	public ProductList executeReadStrategy() throws IOException {
		if(this.strategy != null) {
			this.strategy.open();
			product = this.strategy.readObject();
			this.strategy.close();

    		for (Product product: productList) {
    			productList.add(product);
    		}

			return productList;
		}
		return null;
	}

	public void executeWriteStrategy(Product p) throws IOException {
		if(this.strategy != null) {
			this.strategy.open();
			this.strategy.writeObject(p);
			this.strategy.close();
		}
	}

}
