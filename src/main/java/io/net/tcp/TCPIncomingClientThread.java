package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.Callable;

import controller.BuyController;
import fpt.com.Order;

public class TCPIncomingClientThread extends Thread implements Callable<Order>{

	private InputStream inStream;
	private ObjectInputStream ois;
	private Order order;
	private BuyController controller;

	public TCPIncomingClientThread(InputStream inStream, BuyController controller) {
		this.inStream = inStream;
		this.controller = controller;
	}

	public void run() {
		System.out.println("[TCPClient] Warte auf Serverantwort.");

		try {
			this.ois = new ObjectInputStream(this.inStream);

			order = (Order) this.ois.readObject();
			System.out.println("---------------------------------------------------------------");
			System.out.println(order.getSum());
			System.out.println("---------------------------------------------------------------");
			this.controller.setResult(order);

//			Object obj;
//			while((obj = ois.readObject()) != null) {
//
//				if(obj instanceof Boolean) {
//					if(obj.equals(true)) {
//						System.out.println("[TCPCLient] Order erfolgreich auf Server eingegangen.");
//					}
//				}
//
//			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Order call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
