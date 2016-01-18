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
	private static final int MAX_QUEUESIZE = 8;
	private static final int MAX_CASHPOINTS = 6;

	public Acquisition(Cashpoint[] cashpoints) {
		this.cashpoints = cashpoints;
		this.threads = new Thread[MAX_CASHPOINTS];
	}

	@Override
	public void run() {
		while(!allCashpointsFull()) {





			Client client = new Client();
			double p1 = client.shutUpAndTakeMyMoney();
			Client cl2 = new Client();

			cashpoints[0].addClient(client, p1);
			cashpoints[0].addClient(cl2, p1);
			cashpoints[0].addClient(cl2, p1);

			cashpoints[1].addClient(cl2, p1);
			cashpoints[1].addClient(cl2, p1);
			cashpoints[1].addClient(cl2, p1);
			cashpoints[1].addClient(cl2, p1);
			cashpoints[1].addClient(cl2, p1);
			cashpoints[1].addClient(cl2, p1);

			for(int x = 0; x < MAX_CASHPOINTS; x++) {
				cashpoints[0].addClient(client, p1);
			}



//			queueSizes.clear();
//			for(Cashpoint cashpoint : cashpoints) {
//
//			}

			for(int i = 0; i < MAX_CASHPOINTS; i++) {
				System.out.println("in for");
				if(cashpoints[i].getQueueSize() == MAX_CASHPOINTS) {
					System.out.println("Kasse voll.");

					for(int j = 0; j < MAX_CASHPOINTS; j++) //nächst kleinere Kasse finden
						if(cashpoints[j].getQueueSize() < MAX_CASHPOINTS) {
							threads[i+1] = new Thread(cashpoints[j]);
							threads[i+1].start();
						}
				}
			}


			Thread cpt1 = new Thread(cashpoints[0]);
			cpt1.start();
			Thread cpt2 = new Thread(cashpoints[1]);
			cpt2.start();



			try {
				cpt1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private synchronized boolean allCashpointsFull() {
		for (Cashpoint cashpoint : cashpoints) {
			if(cashpoint.getQueueSize() == 8) {
				return true;
			}
		}
		return false;
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
