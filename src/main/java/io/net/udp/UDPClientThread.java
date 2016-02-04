package io.net.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class UDPClientThread extends Thread {

	private DatagramPacket packet;
	private DatagramSocket socket;

	public UDPClientThread(DatagramPacket packet, DatagramSocket socket)
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

		System.out.printf("Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
				address, port, len, new String(data, 0, len));

		// Nutzdaten in ein Stringobjekt  übergeben und nach "time" suchen
		String da = new String(packet.getData());
		Scanner sc = new Scanner(da);
		String keyword = sc.findInLine("time");

		if (keyword.equals("time")) {

			// Datum abrufen, ins gewünschte Format bringen und in Byte-Sequenz umwandeln
			Date dt = new Date();
			SimpleDateFormat sdt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String dt2 = sdt.format(dt);

			byte[] myDate = dt2.toString().getBytes();

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
				sleep(5000);
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
