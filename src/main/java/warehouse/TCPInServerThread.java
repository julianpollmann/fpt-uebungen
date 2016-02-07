package warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import fpt.com.Order;
import javafx.util.Pair;
import fpt.com.Product;

public class TCPInServerThread extends Thread {

	private InputStream inStream;
	private enum State {
		LOGIN,
		DATA
	};
	private Order orderList;
	private ObjectInputStream ois;
	private Order order;

	public TCPInServerThread(InputStream inStream, Order orderList) {
		this.inStream = inStream;
		this.orderList = orderList;
	}

	public void run() {
		System.out.println("[TCPServer] Verbindung " + Thread.currentThread().getId() + " hergestellt");

		State st = State.LOGIN;
		Object obj;
		try {
			this.ois = new ObjectInputStream(this.inStream);
			while((obj = ois.readObject()) != null) {

				// Login
				if(obj instanceof Pair<?, ?>) {
					st = checkAuth((Pair<String, String>) obj, st);
				}

				// Order
				if(st == State.DATA) {
					if(obj instanceof fpt.com.Order) {
						getCurrentOrders(obj);

						getAllOrders();
					}
				}

			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prints current Order and adds
	 * products of Order to orderList
	 * @param Order obj
	 */
	private void getCurrentOrders(Object obj) {
		order = ((Order) obj);
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
