package warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import fpt.com.Order;

public class TCPServerConnector {

	private InputStream inStream;
	private OutputStream outStream;
	private Order orderList;
	private TCPInServerThread inThread;
	private TCPOutServerThread outThread;
	private BlockingQueue<Order> messages;

	public TCPServerConnector(InputStream inStream, OutputStream outStream, Order orderList) {
		this.inStream = inStream;
		this.outStream = outStream;
		this.orderList = orderList;
		messages = new ArrayBlockingQueue<>(30);
	}

	public void bindStreams() {
		inThread = new TCPInServerThread(this.inStream, this.orderList, messages);
		outThread = new TCPOutServerThread(this.outStream, messages);

		inThread.start();
		outThread.start();

		try {
			inThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			outThread.join();
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
