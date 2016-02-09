package controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.net.udp.UDPClient;
import view.ViewCustomer;

public class UDPClientController extends Thread {

	UDPClient currentClient;
	ViewCustomer temp;

	public UDPClientController(ViewCustomer temp){
		
		currentClient = new UDPClient(); 
		this.temp = temp;
	}

	public void run(){


		System.out.println(currentClient.getTime());
		


			}



}
