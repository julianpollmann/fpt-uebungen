package problem4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import problem4.*;

public class MainClass {

	public static void main(String[] args) {
//		WaitingQueue1 queue = new WaitingQueue1();

		BlockingQueue<Client> queue = new ArrayBlockingQueue<>(8);

		Thread t1 = new Thread(new Acquisition(queue));
		Thread t2 = new Thread(new Cashpoint(queue));
		t1.start();
		t2.start();
	}

}
