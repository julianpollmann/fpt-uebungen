package warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;

import fpt.com.Order;
import fpt.com.Product;
import javafx.util.Pair;

public class TCPInServerThread extends Thread {

	private InputStream inStream;
	private enum State {
		LOGIN,
		DATA
	};
	private Order orderList;
	private ObjectInputStream ois;
	private Order order;
	private BlockingQueue<Order> messages;
	private Pair<String, String> login;

	public TCPInServerThread(InputStream inStream, Order orderList, BlockingQueue<Order> messages) {
		this.inStream = inStream;
		this.orderList = orderList;
		this.messages = messages;
	}

	public void run() {
		System.out.println("[TCPServer] Verbindung " + Thread.currentThread().getId() + " hergestellt");

		State st = State.LOGIN;
		try {
			this.ois = new ObjectInputStream(this.inStream);

			// Login
			login = (Pair<String, String>) this.ois.readObject();
			st = checkAuth(login, st);

			// Order
			if(st == State.DATA) {
				order = (Order) this.ois.readObject();
				getCurrentOrders(order);

				// Pass message to OutgoingThread
				try {
					this.messages.put(order);
//					synchronized(this.messages) {
//						this.messages.put(order);
//						notify();
//					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				getAllOrders();
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			// Wenn wir das hier tun, ist der OutputStream des Clients kaputt :/
//			try {
//				ois.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	/**
	 * Prints current Order and adds
	 * products of Order to orderList
	 * @param Order obj
	 */
	private void getCurrentOrders(Order order) {
		System.out.println("[TCPServer] Order eingegangen:");
		System.out.println("[TCPServer] +++++++++++++++++++++++++++++");

		for(Product product : order) {
			System.out.println(
					"[TCPServer] " +
					product.getName() + "\t" +
					product.getQuantity() + "\t" +
					product.getPrice() + " EUR"
			);
			synchronized(this.orderList) {
				this.orderList.add(product);
			}
		}
		System.out.println("[TCPServer] +++++++++++++++++++++++++++++");
	}

	/**
	 * Checks if user is logged in
	 *
	 * @param  Pair with username, password
	 * @param  current State
	 * @return changed State
	 */
	private State checkAuth(Pair<String, String> obj, State st) {
		System.out.println("[TCPServer] Prüfe Nutzername/Passwort");

		if (obj.getKey().equals("admin") && obj.getValue().equals("admin")) {
			System.out.println("[TCPServer] Login korrekt.");
			st = State.DATA;
		} else {
			System.out.println("[TCPServer] Login fehlerhaft.");
		}
		return st;
	}

	/*
	 * Get all orders + sums
	 */
	private void getAllOrders() {
		System.out.println("[TCPServer] Alle Einkäufe:");
		synchronized(this.orderList) {
			for(Product prod : this.orderList) {
				System.out.println("[TCPServer] " + prod.getName() + "\t" + prod.getQuantity() + "\t" + prod.getPrice() + " EUR");
			}
			System.out.println("[TCPServer] +++++++++++++++++++++++++++++");
			System.out.println("[TCPServer] Gesamtanzahl: \t" + this.orderList.getQuantity());
			System.out.println("[TCPServer] Gesamtpreis: \t" + this.orderList.getSum() + " EUR");
		}
	}
}
