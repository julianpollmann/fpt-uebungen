package warehouse;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import fpt.com.Order;
import problem4.Client;
import problem4.WaitingQueue;

public class TCPServerConnector {

	private InputStream inStream;
	private OutputStream outStream;
	private Order orderList;
	private TCPInServerThread inThread;
	private TCPOutServerThread outThread;
	private int connectionId;
	private BlockingQueue<Order> messages;

	public TCPServerConnector(int connectionId, InputStream inStream, OutputStream outStream, Order orderList) {
		this.inStream = inStream;
		this.outStream = outStream;
		this.orderList = orderList;
		this.connectionId = connectionId;
		this.messages = new ArrayBlockingQueue<>(30);

		bindStreams();
	}

	private void bindStreams() {
		inThread = new TCPInServerThread(this.inStream, orderList, this.messages);
		outThread = new TCPOutServerThread(this.outStream, orderList, this.messages);

		inThread.start();
		outThread.start();


	}
}
