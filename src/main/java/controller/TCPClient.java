package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fpt.com.Product;
import io.OutgoingThread;
import javafx.util.Pair;

public class TCPClient {

	private Socket serverCon;
	private ExecutorService threadPool;
	private Product prod;
	private Pair<String, String> loginResult;

	public TCPClient(Pair<String, String> loginResult, Product prod) {
		threadPool = Executors.newCachedThreadPool();
		this.loginResult = loginResult;
		this.prod = prod;
	}

	public void openConnection() {
		try(Socket serverCon = new Socket("localhost", 6666);
				OutputStream outStream = serverCon.getOutputStream()) {
			threadPool.execute(new OutgoingThread(outStream, loginResult, prod));
			OutgoingThread outT1 = new OutgoingThread(outStream, loginResult, prod);
			outT1.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
