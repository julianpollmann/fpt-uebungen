package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import fpt.com.Product;
import javafx.util.Pair;

public class OutgoingThread extends Thread {

	private Pair<String, String> loginResult;
	private Product order;
	private InputStream input;
	private OutputStream output;
	private byte[] byteArray;

	public OutgoingThread(Pair<String, String> loginResult, Product order) {
		this.loginResult = loginResult;
		this.order = order;
	}

	public void run() {
		openConnection();
	}

	private void openConnection() {
		try (Socket serverCon = new Socket("localhost", 6666);
				OutputStream output = serverCon.getOutputStream()) {

			byte[] byteArrayUser = convertStringToByte(this.loginResult.getKey());
			byte[] byteArrayPass = convertStringToByte(this.loginResult.getValue());
			byte[] byteArrayOrder = convertStringToByte(this.loginResult.getKey());


			// Zahlenschreiben schreiben
			output.write(byteArrayUser);
			output.write(byteArrayPass);
			output.write(byteArrayOrder);
			output.flush();

			// Ergebnis entgegennehmen
			int result = input.read();

			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] convertStringToByte(String string) throws UnsupportedEncodingException {
		int length = string.length();

		byte[] byteArray = new byte[length];
		byteArray = string.getBytes("UTF-8");

		return byteArray;
	}

}
