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

		boolean first = true;

		while((queue.size() != 0) || first) {

			try {
				int randSec = (int)(Math.random()*4000+6000);
				Thread.sleep(randSec);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			synchronized(queue) {
				while(queue.size() < 1) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}

				try {
					queue.take();
					System.out.println("Kunde konsumiert");
					System.out.println(" (verbleiben: " + queue.size() + ")");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			first = false;

		}


	}

}
