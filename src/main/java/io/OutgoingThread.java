package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import fpt.com.Product;
import javafx.util.Pair;

public class OutgoingThread extends Thread {

	private OutputStream outStream;
	private Socket serverCon;
	private Pair<String, String> loginResult;
	private Product order;
	private String user;
	private String pass;
	private String prodName;
	private int prodQuantity;
	private double prodPrice;

//	public OutgoingThread(Socket serverCon, Pair<String, String> loginResult, Product order) {
//		this.serverCon = serverCon;
//		this.loginResult = loginResult;
//		this.order = order;
//	}
	public OutgoingThread(OutputStream outStream, Pair<String, String> loginResult, Product order) {
		this.outStream = outStream;
		this.loginResult = loginResult;
		this.order = order;
	}

	public void run() {
			PrintWriter outPrintWriter = new PrintWriter(this.outStream, true);
			System.out.println("Thread running");
			user = this.loginResult.getKey().toString();
			pass = this.loginResult.getValue().toString();

			System.out.println(user);

			try {
				this.outStream.write(user.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			outPrintWriter.print("login=" + user + ":" + pass + "\r\n");
			outPrintWriter.println(order.getName());
			outPrintWriter.println(order.getQuantity());
			outPrintWriter.println(order.getPrice());

//			outPrintWriter.flush();
			try {
				this.outStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
