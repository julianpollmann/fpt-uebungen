package io.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import fpt.com.Product;
import javafx.util.Pair;

public class TCPOutgoingClientThread extends Thread {

	private OutputStream outStream;
	private PrintStream printStream;
	private Pair<String, String> loginResult;
	private Product order;
	private String user;
	private String pass;

	public TCPOutgoingClientThread(OutputStream outStream, Pair<String, String> loginResult, Product order) {
		this.outStream = outStream;
		this.loginResult = loginResult;
		this.order = order;
	}

	public void run() {
		user = this.loginResult.getKey().toString();
		pass = this.loginResult.getValue().toString();

		printStream = new PrintStream(this.outStream);
		printStream.println("login=" + user + ":" + pass + "\r\n");

		printStream.println("ProdName=" + this.order.getName());
		printStream.println("ProdQuantity=" + this.order.getQuantity());
		printStream.println("ProdPrice=" + this.order.getPrice());

		printStream.flush();
	}
}
