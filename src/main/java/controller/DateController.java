package controller;

import java.io.IOException;
import java.net.*;

class DateController {

	public static void main(String[] args) {
		// Eigene Adresse erstellen
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		// Socket f�r den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(5555);) {

			try {
				int i = 0;
				while (i < 10) {
					String command = "DATE:";

					byte buffer[] = null;
					buffer = command.getBytes();

					// Paket mit der Anfrage vorbereiten
					DatagramPacket packet = new DatagramPacket(buffer,
							buffer.length, ia, 3431);
					// Paket versenden
					dSocket.send(packet);

					byte answer[] = new byte[1024];
					// Paket f�r die Antwort vorbereiten
					packet = new DatagramPacket(answer, answer.length);
					// Auf die Antwort warten
					dSocket.receive(packet);

					System.out.println(new String(packet.getData(), 0, packet
							.getLength()));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					i++;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}

	}
}