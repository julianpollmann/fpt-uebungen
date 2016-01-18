package problem4;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Cashpoint implements Runnable, Comparable<Cashpoint>{

	private int id;
	private WaitingQueue<Client> queue;
	private boolean isOpen;

	public Cashpoint(int id) {
		this.id = id;
		this.queue = new WaitingQueue<Client>(6);
		this.isOpen = false;
	}

	@Override
	public void run() {
		System.out.println("in CP run()");

		/*
		 * 3d) Wait 6 Secs before processing Clients
		 */
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Kasse " + this.id + " ist jetzt geöffnet.");


		/*
		 * Processing the WaitingQueue starts here
		 */
		while((queue.size() > 0)) {

			/*
			 * 2a) Processing a Client takes 6-10 Secs
			 */
			try {
				int randSec = (int)(Math.random()*4000+6000);
				Thread.sleep(randSec);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}

			synchronized(queue) {
//				while(queue.size() < 1) {
//					try {
//						this.queue.wait();
//					} catch (InterruptedException e) {
//						System.out.println(e);
//					}
//				}

				try {
					this.queue.take();
					this.queue.notify();
					System.out.print("Kasse " + this.id + " hat einen von "  + (this.queue.size()+1) + " Kunde abgearbeitet");
					System.out.println(" (verbleiben: " + this.queue.size() + ").");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("Kasse " + this.id + " ist jetzt geschlossen.");
	}

	public int getId() {
		return this.id;
	}

	/*
	 * Adds Client to Queue
	 * TODO: Adds Price to Balance
	 */
	public synchronized void addClient(Client client, double price) {
		try {
//			this.queue.add(client);
			this.queue.put(client);
//			this.queue.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Getter for Queue size
	 */
	public synchronized int getQueueSize() {
		return this.queue.size();
	}

	public boolean cpIsOpen() {
		return this.isOpen;
	}



	@Override
	public int compareTo(Cashpoint o) {
		System.out.println("SORTSORTSORT");
		System.out.println(this.getQueueSize());
		System.out.println(o.getQueueSize());

		if(this.getQueueSize() > o.getQueueSize()){

			return 0;
		}else{
			return 1;
		}

	}



}
