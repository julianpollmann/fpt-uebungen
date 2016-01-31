package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import fpt.com.Product;
import javafx.util.Pair;

public class OutgoingThread extends Thread {

	private Pair<String, String> loginResult;
	private Product order;
	private String user;
	private String pass;
	private String prodName;
	private int prodQuantity;
	private double prodPrice;

	public OutgoingThread(Pair<String, String> loginResult, Product order) {
		this.loginResult = loginResult;
		this.order = order;
	}

	public void run() {
		openConnection();
	}

	private void openConnection() {
		try (Socket serverCon = new Socket("localhost", 6666);
				OutputStream outStream = serverCon.getOutputStream();
				PrintWriter outPrintWriter = new PrintWriter(outStream, true)
			) {

			user = this.loginResult.getKey().toString();
			pass = this.loginResult.getValue().toString();

			outPrintWriter.print("login=" + user + ":" + pass + "\r\n");
			outPrintWriter.println(order.getName());
			outPrintWriter.println(order.getQuantity());
			outPrintWriter.println(order.getPrice());

			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
