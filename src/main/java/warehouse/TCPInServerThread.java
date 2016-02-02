package warehouse;

import java.io.BufferedReader;
import java.io.InputStream;

public class TCPInServerThread extends Thread {

	private InputStream inStream;

	public TCPInServerThread(InputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		System.out.println("[TCPServer] Verbindung " + Thread.currentThread().getId() + " hergestellt");
		BufferedReader inReader = null;

	}

}
