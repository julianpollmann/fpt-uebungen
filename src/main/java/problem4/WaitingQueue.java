package problem4;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingQueue<Client> extends ArrayBlockingQueue<Client> {

	public WaitingQueue(int capacity) {
		super(capacity);
	}

}
