package io.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import view.ViewCustomer;

public class UDPClient extends Thread {

	private String time = "";
	private ViewCustomer view;
	private InetAddress ia = null;
	private String command;
	private DatagramPacket packet;

	public UDPClient(ViewCustomer view) {
		this.view = view;
	}

	public void run() {

		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		// Socket für den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(6668)) {

			try {
				while (true) {
					command = "time";

					byte buffer[] = null;
					buffer = command.getBytes();

					// Paket mit der Anfrage vorbereiten
					packet = new DatagramPacket(buffer,
							buffer.length, ia, 6667);
					// Paket versenden
					dSocket.send(packet);

					byte answer[] = new byte[1024];
					// Paket für die Antwort vorbereiten
					packet = new DatagramPacket(answer, answer.length);
					// Auf die Antwort warten
					dSocket.receive(packet);

					time = new String(packet.getData(), 0, packet
							.getLength());

//					System.out.println(time);



					Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                    view.setTime(time);
		                }
		            });


					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}
	}
}
