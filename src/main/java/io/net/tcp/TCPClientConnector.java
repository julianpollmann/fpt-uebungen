package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import controller.BuyController;
import fpt.com.Order;
import javafx.util.Pair;

public class TCPClientConnector {

	private InputStream inStream;
	private OutputStream outStream;
	private Pair<String, String> loginResult;
	private Order order;
	private Thread[] threads;
	private BuyController controller;
	private TCPIncomingClientThread inThread;
	private TCPOutgoingClientThread outThread;

	public TCPClientConnector(InputStream inStream, OutputStream outStream, Pair<String, String> loginResult, Order order, BuyController controller) {
		this.inStream = inStream;
		this.outStream = outStream;
		this.loginResult = loginResult;
		this.order = order;
		this.controller = controller;
	}

	public void bindStreams() {

		inThread = new TCPIncomingClientThread(inStream, this.controller);
		outThread = new TCPOutgoingClientThread(outStream, this.loginResult, this.order);

		inThread.start();
		outThread.start();

		try {
			outThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			inThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			this.inStream.close();
			this.outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
