package warehouse;

import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import fpt.com.Order;
import fpt.com.Product;

public class TCPOutServerThread extends Thread {

	private OutputStream outStream;
	private Order order;
	private Order orderList;
	private BlockingQueue<Order> messages;

	public TCPOutServerThread(OutputStream outStream, Order orderList, BlockingQueue<Order> messages) {
		this.outStream = outStream;
		this.orderList = orderList;
		this.messages = messages;
	}

	public void run() {

	}

}
