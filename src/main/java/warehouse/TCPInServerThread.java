package warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

	public TCPInServerThread(InputStream inStream, List orders) {
		this.inStream = inStream;
		this.orders = orders;
	}

	public void run() {
		System.out.println("[TCPServer] Verbindung " + Thread.currentThread().getId() + " hergestellt");
		BufferedReader inReader = null;
		State st = State.LOGIN;

		// TODO: ist das hier richtig, um InStream zu synchronisieren?
		synchronized(inStream) {
			inReader = new BufferedReader(new InputStreamReader(inStream));

			String inputLine;
			try {
				// TODO: Bug, Teil nach der while Schleife wird nicht ausgeführt?!
				while((inputLine = inReader.readLine()) != null) {
				System.out.println(inputLine);
					switch (st) {
					case LOGIN:
						st = checkAuth(st, inputLine);
						break;
					case DATA:
						if(inputLine.startsWith("ProdName=")) {
							prodName = inputLine.substring(9);
						};
						if(inputLine.startsWith("ProdQuantity=")) {
							prodQuantity = Integer.parseInt(inputLine.substring(13));
						};
						if(inputLine.startsWith("ProdPrice=")) {
							prodPrice = Double.parseDouble(inputLine.substring(10));
						}
						if(prodName != null && prodQuantity != 0 && prodPrice != 0) {
							addProd(prodName, prodPrice, prodQuantity);
						}
						break;
					}
				}
				getAllOrders();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	 * Check if user is logged in
	 */
	private State checkAuth(State st, String inputLine) throws IOException {
		System.out.println("[TCPServer] Prüfe Nutzername/Passwort");
		if(inputLine.startsWith("login=")) {
			if(inputLine.equals("login=admin:admin")) {
				System.out.println("[TCPServer] Login korrekt.");
				st = State.DATA;
			} else {
				System.out.println("[TCPServer] Login fehlerhaft.");
			}
		}
		return st;
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
