package problem4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.collections.list.TreeList;

public class Acquisition implements Runnable {

	private Client client;
	private Cashpoint[] cashpoints;
	private Thread[] threads;
	private int cashpointIndex;
	private static final int MAX_QUEUESIZE = 8;
	private static final int MAX_CASHPOINTS = 6;
	private boolean allOpen;

	public Acquisition(Cashpoint[] cashpoints) {
		this.cashpoints = cashpoints;
		threads = new Thread[MAX_CASHPOINTS];
		cashpointIndex = 0;
	}

	@Override
	public void run() {
		for (int ci = 0; ci < MAX_CASHPOINTS; ci++) {
			threads[ci] = new Thread(this.cashpoints[ci]);
		}
		threads[cashpointIndex].start();


		/*
		 * 2d) Check if all Cashpoints are full (one Cashpoint has a
		 * Waiting Queue == 8
		 * If true, wait for Subthreads to be finished
		 * If false, generate Clients and put them in a Queue
		 */
		while(!allCashpointsFull()) {


			if(!allCashpointsOpen()) {


				/*
				 * 2c) It takes 0-1 Secs to generate a new Client
				 */
				try {
					int randSec = (int)(Math.random() * 2000);
					Thread.sleep(randSec);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}


				/*
				 * Generate Client
				 */
				Client client = new Client();
				double p1 = client.shutUpAndTakeMyMoney();


				if(this.cashpoints[cashpointIndex].getQueueSize() < 6) {
					this.cashpoints[cashpointIndex].addClient(client, p1);
				} else {
					// TODO: Logic Fail irgendwo hier
					cashpointIndex = getCashpointWithLowestQueue();
					this.cashpoints[cashpointIndex].addClient(client, p1);
					threads[cashpointIndex].start();
				}

			}



		}

		/*
		 * If every Cashpoint Queue is processed,
		 * close Aquisition
		 */
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Check if all Cashpoints are open
	 */
	private boolean allCashpointsOpen() {
		boolean isOpen = false;
		for (Thread thread : threads) {
			if (!thread.isAlive()) {
				return false;
			}
		}
		System.out.println("Alle Kassen sind offen.");
		return true;
	}

	/*
	 * Check all Cashpoints for full
	 * WaitingQueue
	 */
	private synchronized boolean allCashpointsFull() {
		for (Cashpoint cashpoint : this.cashpoints) {
			if(cashpoint.getQueueSize() == MAX_QUEUESIZE) {
				System.out.println("Alle Warteschlangen voll. Akquiriere keine Neukunden.");
				return true;
			}
		}
		return false;
	}

	/*
	 * Get open Cashpoint with lowest Queue
	 * TODO: Kunden sollen nur in Warteschlange einer offenen Kasse eingereicht werden -> lowestQueueCount muss != 0 sein
	 * Wenn alle offenen Kassen == 6 Person haben, mache neue auf
	 */
	private int getCashpointWithLowestQueue() {
		int lowestQueueCount = MAX_QUEUESIZE;

		for (int c = 0; c < cashpoints.length; c++) {
			if(threads[c].isAlive()) {
				if(cashpoints[c].getQueueSize() < lowestQueueCount) {
					lowestQueueCount = cashpoints[c].getQueueSize();
					cashpointIndex = cashpoints[c].getId()-1;
				}
				if(lowestQueueCount == 6) {
					cashpointIndex++;
				}
			}
		}
		return cashpointIndex;
	}



//				if(thread.isAlive()) {
//					System.out.println("Test" + thread.getId());
//					if(cashpoint.getQueueSize() < lowestQueueCount) {
//						lowestQueueCount = cashpoint.getQueueSize();
//						cashpointIndex = cashpoint.getId()-1;
//						System.out.println("CPIndex: " + cashpointIndex);
//					}
//				}


//			for (int x = 0; x < threads.length; x++) {
//
//				if(threads[x].isAlive()) {
//					System.out.println("Thread läuft: ");
//					if(cashpoint.getQueueSize() < lowestQueueCount) {
//						lowestQueueCount = cashpoint.getQueueSize();
//						cashpointIndex = cashpoint.getId()-1;
//						System.out.println("CPIndex: " + cashpointIndex);
//					}
//				}
//			}

//			if (lowestQueueCount == 6) {
//				cashpointIndex = 0;
//			}



//	@Override
//	public void run() {
//
//
//
//
//		while (queue.size() < 9) {
//
//			Client client = new Client();
//
//			synchronized(queue) {
//				try {
//					queue.add(client);
//					queue.put(client);
//					System.out.println("Kunde hinzugefügt");
//					queue.notify();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			try {
//				int randSec = (int)(Math.random() * 2000);
//				Thread.sleep(randSec);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//
//		}
//
//
////		try {
////			long randSec = (new Random().nextInt((10 - 6) + 1) + 6) * 1000;
////			Thread.sleep(1000);
////			queue.put(new Client());
////			System.out.println("neuer Kunde hinzugefügt");
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
////		System.out.println("Test Acq");
//	}


}
