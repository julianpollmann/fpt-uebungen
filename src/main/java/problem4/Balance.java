package problem4;

import java.util.HashMap;
import java.util.Map;

public class Balance {

	private static final int MAX_CASHPOINTS = 6;
	private Cashpoint[] cashpoints;
	private Cashpoint[] sortedCashpoints;
	private Cashpoint cashpoint;
	private Map<Cashpoint, Double> cashpointBalance;
	private int cashpointIndex;
	private double price;
	private int[] sales;

	public Balance() {
		cashpoints = new Cashpoint[MAX_CASHPOINTS];
		sortedCashpoints = new Cashpoint[MAX_CASHPOINTS];
		cashpointBalance = new HashMap<>();
	}

	public void addSales(Cashpoint cashpoint, double price) {
		this.cashpoint = cashpoint;
		this.price = price;
		this.cashpoint.getId();
		System.out.println("Preis: " + price);
		cashpointBalance.put(this.cashpoint, price);
	}




	//Bubblesort zum Ordnen der Kassen in der Bilanz - Kasse mit größtem Umsatz soll auf Position 0
//	public static void bubblesort(Cashpoint[] cashpoints2) {
//		int temp;
//		boolean unsortiert = true;
//
////		for (int i = 1; i < cashpoints2.length; i++) {
////			System.out.println("Fst loop ");
////			System.out.println("i = " + i);
////			System.out.print(cashpoints2[1].getQueueSize() + " ");
////
////			for(int j = 0; j < cashpoints2.length-i; j++) {
////				System.out.println("Scnd loop ");
////				System.out.println("j = " + j);
////
////				System.out.print(cashpoints2[1].getQueueSize() + " ");
////				System.out.println(cashpoints2[i].getQueueSize());
////
////
////			}
////		}
//
//		for(int i = 1; i < cashpoints2.length; i++) {
//			for(int j = 0; j < cashpoints2.length-i; j++) {
//				System.out.println(cashpoints2[i].getQueueSize());
//				System.out.println(cashpoints2[j].getQueueSize());
//				if(cashpoints2[i].getQueueSize() < cashpoints2[j].getQueueSize()) {
//					temp = j;
//
//				}
//			}
//		}
//
//
//
//	}



//	if(cashpoints2[j].getQueueSize() > cashpoints2[j+1].getQueueSize()) {
//		temp = cashpoints2[j].getId();
//		System.out.println("ID ist: " + temp);
//
//	}


//		int temp;
//		for(int i = 1; i < cashpoints2.length; i++) {
//			for(int j = 0; j < cashpoints2.length-i; j++) {
////				if(cashpoints2[j]<cashpoints2[j+1]) {
////					temp=cashpoints2[j];
////					cashpoints2[j]=cashpoints2[j+1];
////					cashpoints2[j+1]=temp;
////				}
//
//			}
//		}
//		return cashpoints2;
//	}


	//Test zum Sortieren
//	public static void main(String[] args) {
//
//		int arrayList[]={200,500,3000,20,50};
//
//		int cashpointIndex = 0;
//		Cashpoint[] cashpoints = {
//				new Cashpoint(1),
//				new Cashpoint(2),
//				new Cashpoint(3)
//		};
//
//		Client client = new Client();
//		double p1 = client.shutUpAndTakeMyMoney();
//
//		cashpoints[cashpointIndex].addClient(client, p1);
//		cashpoints[cashpointIndex].addClient(client, p1);
//		cashpoints[cashpointIndex].addClient(client, p1);
//
//		cashpointIndex = 1;
//		cashpoints[cashpointIndex].addClient(client, p1);
//		cashpoints[cashpointIndex].addClient(client, p1);
//
//		cashpointIndex = 2;
//		cashpoints[cashpointIndex].addClient(client, p1);
//
////		for(Cashpoint cashpoint : cashpoints) {
////			System.out.println(cashpoint.getId());
////		}
//
//
//		bubblesort(cashpoints);
//
////		int[] sortiert=bubblesort(arrayList);
//
////		for (int i = 0; i<sortiert.length; i++) {
////			System.out.print(sortiert[i] + ", ");
////		}
//
//	}
	 //benutze PriorityBlockingQueue hier
}


