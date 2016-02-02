package warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Incoming extends Thread {

	private int id;
	private final Socket socket;
	private enum State {
		LOGIN,
		DATA
	};

	public Incoming(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("[TCPServer] Verbindung " + id + " hergestellt");
		BufferedReader inReader = null;
		State st = State.LOGIN;

		try {
			InputStream inStream = this.socket.getInputStream();
			inReader = new BufferedReader(new InputStreamReader(inStream));

			String inputLine;
			while((inputLine = inReader.readLine()) != null) {
				System.out.println(inputLine);
				switch (st) {
					case LOGIN:
						st = checkAuth(st, inputLine);
						break;
					case DATA:
						System.out.println(inputLine);
						break;
				}


			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("[TCPServer] Verbindung " + id + " wird beendet");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private State checkAuth(State st, String inputLine) throws IOException {
		if(inputLine.startsWith("login=")) {
			if(inputLine.equals("login=admin:admin")) {
				System.out.println("[TCPServer] Login correct.");
				st = State.DATA;
			} else {
				System.out.println("[TCPServer] Login incorrect. Socket close.");
				socket.close();
			}
		}
		return st;
	}
}
