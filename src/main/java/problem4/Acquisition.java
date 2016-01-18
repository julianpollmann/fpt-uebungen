package problem4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.collections.list.TreeList;

public class Acquisition implements Runnable {

	private Client client;
	private WaitingQueue<Client> queue;
	private WaitingQueue<Client>[] queues;
	private List<Integer> queueSizes;
	private Cashpoint[] cashpoints;
	private Thread[] threads;
	private int cashpointPointer;
	private static final int MAX_QUEUESIZE = 8;
	private static final int MAX_CASHPOINTS = 6;

	public Acquisition(Cashpoint[] cashpoints) {
		this.cashpoints = cashpoints;
		this.threads = new Thread[MAX_CASHPOINTS];
		this.cashpointPointer = 0;
	}

	@Override
	public void run() {
		while(!allCashpointsFull()) {

			try {
				int randSec = (int)(Math.random() * 2000);
				Thread.sleep(randSec);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			Client client = new Client();
			double p1 = client.shutUpAndTakeMyMoney();
			cashpoints[0].addClient(client, p1);






//			for(int i = 0; i < MAX_CASHPOINTS; i++) {
//				System.out.println("in for");
//				if(cashpoints[i].getQueueSize() == MAX_CASHPOINTS) {
//					System.out.println("Kasse voll.");
//
//					for(int j = 0; j < MAX_CASHPOINTS; j++) //nächst kleinere Kasse finden
//						if(cashpoints[j].getQueueSize() < MAX_CASHPOINTS) {
//							threads[i+1] = new Thread(cashpoints[j]);
//							threads[i+1].start();
//						}
//				}
//			}

			for(int y = 0; y < MAX_CASHPOINTS; y++) {
				this.threads[y] = new Thread(cashpoints[y]);
				this.threads[y].start();
			}


//			Cashpoint lowestCP = getCashpointWithLowestQueue();
//			System.out.println(lowestCP.getId());

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
	 * Check all Cashpoints for full
	 * WaitingQueue
	 */
	private synchronized boolean allCashpointsFull() {
		for (Cashpoint cashpoint : cashpoints) {
			if(cashpoint.getQueueSize() == 8) {
				System.out.println("CP ist voll!!!");
				return true;
			}
		}
		return false;
	}

	/*
	 * Get the Cashpoint with the lowest Queue
	 */
	private Cashpoint getCashpointWithLowestQueue() {
		int lowestQueueCount = MAX_QUEUESIZE;

		for (Cashpoint cashpoint : cashpoints) {
			if(cashpoint.getQueueSize() < lowestQueueCount) {
				lowestQueueCount = cashpoint.getQueueSize();
				this.cashpointPointer = cashpoint.getId();
			}
		}
		return cashpoints[this.cashpointPointer];
	}


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
