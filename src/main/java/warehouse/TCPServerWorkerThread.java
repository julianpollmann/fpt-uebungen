package warehouse;

import java.io.InputStream;
import java.io.OutputStream;

public class TCPServerWorkerThread extends Thread {

	private InputStream inStream;
	private OutputStream outStream;

	public TCPServerWorkerThread(InputStream inStream, OutputStream outStream) {
		this.inStream = inStream;
		this.outStream = outStream;
	}
	
	@Override
	public void run() {
		
	}

}
