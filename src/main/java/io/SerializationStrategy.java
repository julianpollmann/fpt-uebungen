package io;

import java.io.IOException;

import fpt.com.SerializableStrategy;

public class SerializationStrategy {

	private SerializableStrategy strategy;

	public SerializationStrategy(SerializableStrategy strategy) {
		this.strategy = strategy;
	}

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
	public void executeWriteStrategy() throws IOException {
		if(this.strategy != null) {
			this.strategy.open();
			this.strategy.writeObject(null);
			this.strategy.close();
		}
	}

}
