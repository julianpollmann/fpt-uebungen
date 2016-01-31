package warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Incoming implements Runnable {

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

		try (InputStream inStream = socket.getInputStream()) {
			inReader = new BufferedReader(new InputStreamReader(inStream));

			String inputLine;
			while((inputLine = inReader.readLine()) != null) {
				switch (st) {
					case LOGIN:
						st = checkAuth(st, inputLine);
						break;
					case DATA:
						System.out.println(inputLine);
						break;
				}


			}



			System.out.println("[TCPServer] Verbindung " + id + " wird beendet");
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
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

	public int getId() {
		return this.id;
	}

}
