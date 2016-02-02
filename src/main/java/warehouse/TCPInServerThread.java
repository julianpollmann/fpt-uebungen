package warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TCPInServerThread extends Thread {

	private InputStream inStream;
	private enum State {
		LOGIN,
		DATA
	};
	private String prodName;
	private int prodQuantity;
	private double prodPrice;

	public TCPInServerThread(InputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		System.out.println("[TCPServer] Verbindung " + Thread.currentThread().getId() + " hergestellt");
		BufferedReader inReader = null;
		State st = State.LOGIN;


		inReader = new BufferedReader(new InputStreamReader(inStream));

		String inputLine;
		try {
			while((inputLine = inReader.readLine()) != null) {
//				System.out.println(inputLine);
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
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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

}
