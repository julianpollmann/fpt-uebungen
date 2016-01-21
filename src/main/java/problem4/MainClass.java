package problem4;

import problem4.*;

public class MainClass {

	public static void main(String[] args) {
//		WaitingQueue1 queue = new WaitingQueue1();

		Balance balance = new Balance();

		Cashpoint[] cashpoints = {
				new Cashpoint(1, balance),
				new Cashpoint(2, balance),
				new Cashpoint(3, balance),
				new Cashpoint(4, balance),
				new Cashpoint(5, balance),
				new Cashpoint(6, balance)
		};

		Acquisition ac = new Acquisition(cashpoints);
		Thread t1 = new Thread(ac);
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Alle Kunden abgearbeitet + Kundenakquise gestoppt.");
	}
}
