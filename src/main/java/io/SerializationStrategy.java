package io;

import java.io.IOException;

import model.Product;
import fpt.com.SerializableStrategy;


public class SerializationStrategy {

	private SerializableStrategy strategy;

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

	// TODO: Rückgabewert + Param
	public void executeReadStrategy() throws IOException {
		if(this.strategy != null) {
			this.strategy.open();
			this.strategy.readObject();
			this.strategy.close();
		}
	}

	// TODO: Rückgabewert + Param
	public void executeWriteStrategy(Product p) throws IOException {
		if(this.strategy != null) {
			this.strategy.open();
			this.strategy.writeObject(p);
			this.strategy.close();
		}
	}

}
