package problem4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Cashpoint implements Runnable {

	private BlockingQueue<Client> queue;

	public Cashpoint(BlockingQueue<Client> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

//		while(true) {
//			data = queue.dequeue();
//		}

		try {
			Client client;

			while(queue.take()) {
				long randSec = (new Random().nextInt(2) * 1000);
				Thread.sleep(1000);
				queue.take();
				System.out.println("Kunde abgefertigt");
			}

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("Test Cash");
	}

}
