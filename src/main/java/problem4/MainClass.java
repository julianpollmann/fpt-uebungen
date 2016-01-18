package problem4;

import problem4.*;

public class MainClass {

	public static void main(String[] args) {
//		WaitingQueue1 queue = new WaitingQueue1();

		Cashpoint[] cashpoints = {
				new Cashpoint(1),
				new Cashpoint(2),
				new Cashpoint(3),
				new Cashpoint(4),
				new Cashpoint(5),
				new Cashpoint(6)
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
