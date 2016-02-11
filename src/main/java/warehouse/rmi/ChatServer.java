package warehouse.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import io.net.rmi.ClientService;

public class ChatServer extends UnicastRemoteObject implements ChatService {

	private ArrayList<String> userList;
	private String user;
	private String message;
	private Registry registry;
	private ClientService client;

	public ChatServer(Registry registry) throws RemoteException {
		super();
		this.registry = registry;
		userList = new ArrayList<String>();
	}

	/*
	 * Adds logged in user to userList
	 */
	@Override
	public void login(String user) throws RemoteException {
		this.user = user;
		System.out.println("[Chatserver] " + this.user + " hat sich eingeloggt.");
		userList.add(this.user);
	}

	/*
	 * Removes logged out user from userList
	 */
	@Override
	public void logout(String user) throws RemoteException {
		this.user = user;
		System.out.println("[Chatserver] " + this.user + " hat sich ausgeloggt.");
		userList.remove(this.user);
	}

	/*
	 * Gets message as param from client and
	 * sends it to every Client in userList
	 */
	@Override
	public void send(String message) throws RemoteException {
		this.message = message;
		System.out.println("[Chatserver] " + this.user + ": " + this.message);
		for(String user : getUserList()) {
			try {
				client = (ClientService) this.registry.lookup(this.user);
				client.setMessage(this.user + ": " + this.message);
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<String> getUserList() throws RemoteException {
		return this.userList;
	}

}
