package problem4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import problem4.*;

public class MainClass {

	public static void main(String[] args) {
//		WaitingQueue1 queue = new WaitingQueue1();

		BlockingQueue<Client> queue = new ArrayBlockingQueue<>(9);

		Acquisition ac = new Acquisition(queue);
		Cashpoint c = new Cashpoint(queue);
		Thread t1 = new Thread(ac);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
	}

}
