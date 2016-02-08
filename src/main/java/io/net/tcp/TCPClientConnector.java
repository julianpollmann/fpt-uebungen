package io.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Order;
import javafx.util.Pair;

public class TCPClientConnector {

	private int id;
	private InputStream inStream;
	private OutputStream outStream;
	private Pair<String, String> loginResult;
	private Order order;
	private Thread[] threads;

	public TCPClientConnector(InputStream inStream, OutputStream outStream, Pair<String, String> loginResult, Order order) {
		this.inStream = inStream;
		this.outStream = outStream;
		this.loginResult = loginResult;
		this.order = order;
		this.threads = new Thread[2];

		bindStreams();
//		unbindStreams();
	}

	private void bindStreams() {

		this.threads[0] = new TCPIncomingClientThread(inStream);
		this.threads[1] = new TCPOutgoingClientThread(outStream, this.loginResult, this.order);

		threads[0].start();
		threads[1].start();
	}

	private void unbindStreams() {
		try {
			threads[0].join();
			threads[1].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
