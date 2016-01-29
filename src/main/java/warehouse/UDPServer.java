package warehouse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

public class UDPServer {
	
	

	public static void main(String[] args) {
		// Socket erstellen unter dem der Server erreichbar ist
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(3431);
			while (true) {
				// Neues Paket anlegen
				DatagramPacket packet = new DatagramPacket(new byte[5], 5);
				// Auf Paket warten
				try {
					socket.receive(packet);
					// Empfangendes Paket in einem neuen Thread abarbeiten
					new ClientThread(packet, socket).start();
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

class ClientThread extends Thread {

	private DatagramPacket packet;
	private DatagramSocket socket;

	public ClientThread(DatagramPacket packet, DatagramSocket socket)
			throws SocketException {
		this.packet = packet;
		this.socket = socket;
	}

	public void run() {
		// Daten auslesen
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		int len = packet.getLength();
		byte[] data = packet.getData();

		System.out.printf("Anfrage von %s vom Port %d mit der L�nge %d:%n%s%n",
				address, port, len, new String(data, 0, len));

		// Nutzdaten in ein Stringobjekt �bergeben
		String da = new String(packet.getData());
		// Kommandos sollen durch : getrennt werden
		Scanner sc = new Scanner(da).useDelimiter(":");
		// Erstes Kommando filtern
		String keyword = sc.next();

		if (keyword.equals("DATE")) {

			Date dt = new Date();
			byte[] myDate = dt.toString().getBytes();

			// Paket mit neuen Daten (Datum) als Antwort vorbereiten
			packet = new DatagramPacket(myDate, myDate.length, address, port);

			try {
				// Paket versenden
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			byte[] myDate = new byte[1024];
			myDate = new String("Command unknown").getBytes();
			try {
				sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			// Paket mit Information, dass das Schl�sselwort ung�ltig ist als
			// Antwort vorbereiten
			packet = new DatagramPacket(myDate, myDate.length, address, port);
			try {
				// Paket versenden
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
