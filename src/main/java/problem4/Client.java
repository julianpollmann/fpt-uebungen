package problem4;

import java.util.Random;

public class Client {

	private double price;

	public Client() {
		
	}

	public double shutUpAndTakeMyMoney() {
		price = new Random().nextDouble() * 100;
		return Math.round(price);
	}
}
