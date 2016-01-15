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
		while (queue.size() < 9) {

			Client client = new Client();

			synchronized(queue) {
				try {
					queue.put(client);
					System.out.println("Kunde hinzugefügt");
					queue.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				int randSec = (int)(Math.random() * 2000);
				Thread.sleep(randSec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}


//		try {
//			long randSec = (new Random().nextInt((10 - 6) + 1) + 6) * 1000;
//			Thread.sleep(1000);
//			queue.put(new Client());
//			System.out.println("neuer Kunde hinzugefügt");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Test Acq");
	}


}
