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
	private String prodName;
	private int prodQuantity;
	private double prodPrice;
	private List orders;
	private Product product;
	private int prodQuantityAll;
	private double prodPriceAll;
	private ObjectInputStream ois;
	private Order order;

	public TCPInServerThread(InputStream inStream, List orders) {
		this.inStream = inStream;
		this.orders = orders;
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
						Order order = ((Order) obj);
						System.out.println("[TCPServer] Order eingegangen");
						System.out.println("[TCPServer] +++++++++++++++++++++++++++++");

						for(Product product : order) {
							System.out.println(
									"[TCPServer] " +
									product.getName() + "\t" +
									product.getQuantity() + "\t" +
									product.getPrice() + " EUR"
							);
						}
						System.out.println("[TCPServer] +++++++++++++++++++++++++++++");


						// TODO: Gesamtorder anzeigen...
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
		prodQuantityAll = 0;
		prodPriceAll = 0;
		synchronized(orders) {
			for (int i = 0; i < orders.size(); i++) {
				Product prod = (Product) orders.get(i);
				System.out.println("[TCPServer] " + prod.getName() + "\t" + prod.getQuantity() + "\t" + prod.getPrice() + " EUR");
				prodQuantityAll = prodQuantityAll + prod.getQuantity();
				prodPriceAll = prodPriceAll + prod.getPrice();
			}
		}
		System.out.println("Gesamtanzahl: " + prodQuantityAll);
		System.out.println("Gesamtpreis: " + prodPriceAll);
	}

	/*
	 * Add product to orderlist
	 */
	private void addProd(String prodName, double prodPrice, int prodQuantity) {
		synchronized(orders) {
			orders.add(new Product(prodName, prodPrice, prodQuantity));
		}
		System.out.println("[TCPServer] Order eingegangen");
		System.out.print("[TCPServer] " + prodName + "\t" + prodQuantity + "\t" + prodPrice + " EUR");
	}

}
