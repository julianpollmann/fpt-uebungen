package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.BuyController;
import fpt.com.Order;
import fpt.com.Product;
import javafx.util.Pair;

public class TCPClient {

	private Pair<String, String> loginResult;
	private static Socket clientSocket = null;
	private static OutputStream outStream = null;
	private static InputStream inStream = null;
	private Order order;
	private TCPClientConnector connector;
	private BuyController controller;

	public TCPClient(Pair<String, String> loginResult, Order order, BuyController buyController) {
		this.loginResult = loginResult;
		this.order = order;
		this.controller = buyController;
	}

	public void openConnection() {
		try {
			clientSocket = new Socket("localhost", 6666);
			outStream = clientSocket.getOutputStream();
			inStream = clientSocket.getInputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(clientSocket != null && outStream != null && inStream != null) {
			System.out.println("[TCPClient] Verbindung zu " + clientSocket.getRemoteSocketAddress() + " hergestellt.");

			connector = new TCPClientConnector(inStream, outStream, this.loginResult, this.order, this.controller);
			connector.bindStreams();
		}
	}
}
