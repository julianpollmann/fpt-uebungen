package problem4;

import java.util.Random;

public class Acquisition implements Runnable {

	private Client client;
	private WaitingQueue queue;

	public Acquisition(WaitingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			int n = rand.nextInt();
			queue.aquire(n);
		}
	}


}
