package problem4;

public class Cashpoint implements Runnable {

	private WaitingQueue queue;

	public Cashpoint(WaitingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		int data;

		while(true) {
			data = queue.dequeue();
		}

	}

}
