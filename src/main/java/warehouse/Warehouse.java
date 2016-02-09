package warehouse;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import warehouse.rmi.ChatServer;
import warehouse.rmi.ChatService;

public class Warehouse {

	private static Registry registry;

	public static void main(String[] args) {
//		UDPServer udpserv = new UDPServer();
//		TCPServer tcpserv = new TCPServer();


		try {
			registry = LocateRegistry.createRegistry(1099);
			ChatService rmichat = new ChatServer();
			registry.bind("chatserver", rmichat);
			System.out.println("Chatserver gestartet.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
