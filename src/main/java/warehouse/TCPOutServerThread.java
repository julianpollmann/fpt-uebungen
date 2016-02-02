package warehouse;

import java.io.OutputStream;

public class TCPOutServerThread extends Thread {

	private OutputStream outStream;

	public TCPOutServerThread(OutputStream outStream) {
		this.outStream = outStream;
	}
	
	public void run() {
		
	}

}
