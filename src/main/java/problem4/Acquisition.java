package problem4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Acquisition implements Runnable {

	private Client client;
	private BlockingQueue<Client> queue;

	public Acquisition(BlockingQueue<Client> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
//		while (true) {
//			int n = rand.nextInt();
//			queue.aquire(n);
//		}


		try {
			long randSec = (new Random().nextInt((10 - 6) + 1) + 6) * 1000;
			Thread.sleep(1000);
			queue.put(new Client());
			System.out.println("neuer Kunde hinzugefügt");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Test Acq");
	}


}
