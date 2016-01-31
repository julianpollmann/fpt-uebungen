package warehouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Warehouse {

	private UDPServer udpserv;
	private TCPServer tcpserv;

	public static void main(String[] args) {
//		UDPServer udpserv = new UDPServer();
		TCPServer tcpserv = new TCPServer();
	}
}
