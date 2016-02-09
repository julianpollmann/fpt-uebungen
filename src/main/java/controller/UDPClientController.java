package controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.net.udp.UDPClient;
import view.ViewCustomer;

public class UDPClientController implements Runnable {

	UDPClient currentClient;
	ViewCustomer temp;

	public UDPClientController(){

		currentClient = new UDPClient();

	}

	public void run(){


		System.out.println(currentClient.getTime());



			}


}
