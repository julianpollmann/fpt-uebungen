package warehouse;

import java.net.Socket;

public class Incoming extends Thread {

	private int name;
	private Socket socket;

	public Incoming(int name, Socket socket) {
		this.name = name;
		this.socket = socket;
	}

	public void run() {

	}
}
