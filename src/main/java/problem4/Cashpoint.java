package problem4;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Cashpoint implements Runnable {

	private int id;
	private WaitingQueue<Client> queue;
	private boolean isOpen;
	private static final int MAX_QUEUESIZE = 8;
	private Balance balance;

	public Cashpoint(int id, Balance balance) {
		this.id = id;
		this.balance = balance;
		this.queue = new WaitingQueue<Client>(MAX_QUEUESIZE);
		this.isOpen = false;
	}

	@Override
	public void run() {
		System.out.println("in CP run()");
		this.isOpen = true;

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
		/*
		 * 2b) Waiting Queue is < 0, run() is getting finished now
		 */
		this.isOpen = false;
		System.out.println("Kasse " + this.id + " ist jetzt geschlossen.");
	}

	public int getId() {
		return this.id;
	}

	/*
	 * Adds Client to Queue
	 * TODO: Adds Price to Balance
	 */
	public synchronized boolean addClient (Client client, double price) {
		try {
			this.queue.add(client);
			this.balance.addSales(this, price);
			System.out.println("Kasse " + this.id + " hat einen neuen Kunden (Insgesamt " + this.queue.size() + ").");
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	/*
	 * Getter for Queue size
	 */
	public synchronized int getQueueSize() {
		return this.queue.size();
	}
}
