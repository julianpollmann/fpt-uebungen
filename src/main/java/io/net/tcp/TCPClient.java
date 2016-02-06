package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import fpt.com.Order;
import fpt.com.Product;
import javafx.util.Pair;

public class TCPClient {

	private Pair<String, String> loginResult;
	private static Socket clientSocket = null;
	private static OutputStream outStream = null;
	private static InputStream inStream = null;
	private Order order;
	private Thread[] threads;

	public TCPClient(Pair<String, String> loginResult, Order order) {
		this.loginResult = loginResult;
		this.order = order;
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

			TCPConnector connector = new TCPConnector(inStream, outStream, this.loginResult, this.order);

		}

	}
}
