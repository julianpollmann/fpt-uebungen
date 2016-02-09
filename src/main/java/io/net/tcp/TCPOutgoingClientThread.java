package io.net.tcp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import fpt.com.Order;
import fpt.com.Product;
import javafx.util.Pair;

public class TCPOutgoingClientThread extends Thread {

	private OutputStream outStream;
	private Pair<String, String> loginResult;
	private Order order;
	private ObjectOutputStream oos;

	public TCPOutgoingClientThread(OutputStream outStream, Pair<String, String> loginResult, Order order) {
		this.outStream = outStream;
		this.loginResult = loginResult;
		this.order = order;
	}

	public void run() {

//		user = this.loginResult.getKey().toString();
//		pass = this.loginResult.getValue().toString();

		try {
			oos = new ObjectOutputStream(this.outStream);
			oos.writeObject(this.loginResult);
			oos.writeObject(this.order);
			System.out.println("[TCPClient] Order an Server versendet.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		printStream = new PrintStream(this.outStream);
//		printStream.println("login=" + user + ":" + pass + "\r\n");
//
//		printStream.println("ProdName=" + this.order.getName());
//		printStream.println("ProdQuantity=" + this.order.getQuantity());
//		printStream.println("ProdPrice=" + this.order.getPrice());
//
//		printStream.flush();
	}
}
