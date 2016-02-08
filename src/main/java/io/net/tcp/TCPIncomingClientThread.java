package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import fpt.com.Order;

public class TCPIncomingClientThread extends Thread {

	private InputStream inStream;
	private ObjectInputStream ois;
	private Order order;

	public TCPIncomingClientThread(InputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		System.out.println("[TCPCLient] Warte auf Serverantwort.");

		try {
			this.ois = new ObjectInputStream(this.inStream);

			order = (Order) this.ois.readObject();
			System.out.println("---------------------------------------------------------------");
			System.out.println(order.getSum());
			System.out.println("---------------------------------------------------------------");

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
}
