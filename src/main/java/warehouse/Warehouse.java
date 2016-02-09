package warehouse;

import warehouse.rmi.ChatServer;
import warehouse.rmi.RMIRegistry;

public class Warehouse {

	private UDPServer udpserv;
	private TCPServer tcpserv;

	public static void main(String[] args) {
//		UDPServer udpserv = new UDPServer();
		TCPServer tcpserv = new TCPServer();
		RMIRegistry registry = new RMIRegistry();
		ChatServer rmichat = new ChatServer();
	}
}
