package io.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TCPIncomingClientThread extends Thread {

	private InputStream inStream;

	public TCPIncomingClientThread(InputStream inStream) {
		this.inStream = inStream;
	}

	public void run() {
		System.out.println("in ClientThread Incoming");

		BufferedReader buffRead = new BufferedReader(new InputStreamReader(this.inStream));
		try {
			buffRead.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
