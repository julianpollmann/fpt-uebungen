package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import fpt.com.Product;
import fpt.com.ProductList;
import fpt.com.SerializableStrategy;


public class SerializationStrategy {

	private SerializableStrategy strategy;
	private ProductList productList;
	private Product product;

	/* Basisstrategieklasse
	 * Strategie wird als Parameter übergeben
	 */
	public SerializationStrategy(SerializableStrategy strategy) {
		this.strategy = strategy;
		productList = new model.ProductList();
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

	public ProductList executeReadStrategy(String path) throws IOException {
		if(this.strategy != null) {
			this.strategy.open(path);

//			for(int x = 0; x < strategy.; x++){
//				product = this.strategy.readObject();
//				productList.add(product);
//			}

//			if(this.strategy.readObject() == null) {
//				 test = false;
//			}

			while(true) {

				System.out.println("+++++++++++");
				try  {
					productList.add(this.strategy.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
//				if (this.strategy.readObject() == null) {
//					 test = false;
//				}
			}

			productList.size(); // <- hat das hier eine Funktion?

//			if(product == null) {
//				return productList;
//			}

			System.out.println(productList.size());

//			do{
//				product = this.strategy.readObject();
//				System.out.println("++++++++++++++++++++++++++++++++");
//				System.out.println(product.getName());
//				productList.add(product);
//			} while(product != null);
//			System.out.println(productList.size());


			this.strategy.close();

//    		for (Product product : productList) {
//    			productList.add(product);
//    		}

			return productList;
		}
		return null;
	}

	public void executeWriteStrategy(ProductList productList, String path) throws IOException {
		if(this.strategy != null) {
			// Try-catch für open stream
			try {
				this.strategy.open(path);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Try-catch für writeObject
			try {
				for(Product product : productList) {
					System.out.println(product.getName());
					this.strategy.writeObject(product);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Try-catch für close
			try {
				this.strategy.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
