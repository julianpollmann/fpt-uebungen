package warehouse;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import fpt.com.Order;

public class TCPOutServerThread extends Thread {

	private OutputStream outStream;
	private BlockingQueue<Order> messages;
	private ObjectOutputStream oos;

	public TCPOutServerThread(OutputStream outStream, BlockingQueue<Order> messages) {
		this.outStream = outStream;
		this.messages = messages;
	}

	public void run() {
		try {
			oos = new ObjectOutputStream(this.outStream);
			try {
				oos.writeObject(this.messages.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[TCPServer] Kopie der Order an Client gesendet.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
