package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fpt.com.Product;
import javafx.util.Pair;

public class TCPClient {

	private ExecutorService threadPool;
	private Product prod;
	private Pair<String, String> loginResult;
	private static Socket clientSocket = null;
	private static OutputStream outStream = null;
	private static InputStream inStream = null;

	public TCPClient(Pair<String, String> loginResult, Product prod) {
		threadPool = Executors.newCachedThreadPool();
		this.loginResult = loginResult;
		this.prod = prod;
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
			threadPool.execute(new TCPIncomingClientThread(inStream));
			threadPool.execute(new TCPOutgoingClientThread(outStream, this.loginResult, this.prod));
		}

	}

//	public void openConnection() {
//		try(Socket serverCon = new Socket("localhost", 6666);
//				OutputStream outStream = serverCon.getOutputStream()) {
//			threadPool.execute(new OutgoingThread(outStream, loginResult, prod));
//			OutgoingThread outT1 = new OutgoingThread(outStream, loginResult, prod);
//			outT1.start();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}
