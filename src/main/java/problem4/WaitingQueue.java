package problem4;

public class WaitingQueue {
	private int data;
	private boolean empty;

	public WaitingQueue() {
		this.empty = true;
	}

	public synchronized void aquire(int newData) {
	   while (!this.empty) {
	      try {
	        this.wait();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    this.data = newData;
	    this.empty = false;
	    this.notify();
	    System.out.println("Produced:" + newData);
	}

	public synchronized int dequeue() {
	    while (this.empty) {
	      try {
	        this.wait();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    this.empty = true;
	    this.notify();
	    System.out.println("Consumed:" + data);
	    return data;
	  }
}
