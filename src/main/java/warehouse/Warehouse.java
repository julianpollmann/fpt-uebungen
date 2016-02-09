package warehouse;

import java.rmi.RemoteException;

import warehouse.rmi.ChatServer;
import warehouse.rmi.RMIRegistry;

public class Warehouse {

	public static void main(String[] args) {
//		UDPServer udpserv = new UDPServer();
		TCPServer tcpserv = new TCPServer();

		RMIRegistry registry = new RMIRegistry();
		try {
			ChatServer rmichat = new ChatServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
