package warehouse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TCPOutgoingThread extends Thread {

	private OutputStream outStream;
	private PrintStream printStream;

	public TCPOutgoingThread(OutputStream outStream) {
		this.outStream = outStream;
	}

	public void run() {
		System.out.println("in OutgoignThread");

		try {
			printStream = new PrintStream(this.outStream);
			printStream.println("Test123");
			this.outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
