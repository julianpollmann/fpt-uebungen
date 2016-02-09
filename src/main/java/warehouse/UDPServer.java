package warehouse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

public class UDPServer {

	public UDPServer() {
		startServer();
	}

	public void startServer() {
		// Socket erstellen unter dem der Server erreichbar ist
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(6667);
			System.out.println("[UDPServer] Server lauscht.");

			while (true) {
				// Neues Paket anlegen
				DatagramPacket packet = new DatagramPacket(new byte[5], 5);

				// Auf Paket warten
				try {
					socket.receive(packet);

					System.out.println("[UDPServer] Verbindungaufbau.");

					// Empfangendes Paket in einem neuen Thread abarbeiten
					new UDPServerThread(packet, socket).start();
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