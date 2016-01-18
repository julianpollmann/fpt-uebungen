package problem4;

public class WaitingQueue1 {
	private int data;
	private boolean lock;

	public WaitingQueue1() {
		this.lock = true;
	}

	public synchronized void aquire(int newData) {
	   while (!this.lock) {
	      try {
	        this.wait();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    this.data = newData;
	    this.lock = false;
	    this.notify();
	    System.out.println("Produced:" + newData);
	}

	public synchronized int dequeue() {
	    while (this.lock) {
	      try {
	        this.wait();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	    this.lock = true;
	    this.notify();
	    System.out.println("Consumed:" + data);
	    return data;
	  }
}
