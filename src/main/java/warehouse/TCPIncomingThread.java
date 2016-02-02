package warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TCPIncomingThread extends Thread {

	private InputStream inStream;

	public TCPIncomingThread(InputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		System.out.println("in IncomingThread");

		BufferedReader buffRead = new BufferedReader(new InputStreamReader(this.inStream));
		try {
			System.out.println(buffRead.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
