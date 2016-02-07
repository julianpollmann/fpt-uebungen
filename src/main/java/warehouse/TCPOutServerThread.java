package warehouse;

import java.io.OutputStream;
import java.util.List;

import fpt.com.Order;
import fpt.com.Product;

public class TCPOutServerThread extends Thread {

	private OutputStream outStream;
	private Order order;
	private Order orderList;

	public TCPOutServerThread(OutputStream outStream, Order orderList) {
		this.outStream = outStream;
		this.orderList = orderList;
	}

	public void run() {

	}

}
