package warehouse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

import io.net.udp.UDPClientThread;

public class UDPServer {



	public static void main(String[] args) {
		// Socket erstellen unter dem der Server erreichbar ist
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(6667);
			while (true) {
				// Neues Paket anlegen
				DatagramPacket packet = new DatagramPacket(new byte[5], 5);
				// Auf Paket warten
				try {
					socket.receive(packet);
					// Empfangendes Paket in einem neuen Thread abarbeiten
					new UDPClientThread(packet, socket).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}

	}

}