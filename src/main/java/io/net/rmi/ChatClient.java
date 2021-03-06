package io.net.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import controller.ChatController;
import warehouse.rmi.ChatService;

public class ChatClient extends UnicastRemoteObject implements ClientService {

	private Registry registry;
	private ChatService cServer;
	private String name;
	private ChatController controller;

	/*
	 * Bind this Chatclient with username to registry
	 * and login on ChatServer
	 */
	public ChatClient(ChatController controller, String name) throws RemoteException, NotBoundException, AlreadyBoundException {
		this.registry = LocateRegistry.getRegistry();
		this.cServer = (ChatService) registry.lookup("chatserver");

		this.controller = controller;
		this.name = name;

		this.registry.rebind(this.name, this);
		this.cServer.login(this.name);
	}

	/*
	 * Send message to Server
	 */
	@Override
	public void send(String message) throws RemoteException {
		cServer.send(message);
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	/*
	 * Get message from Server and pass it to Controller
	 */
	@Override
	public void setMessage(String message) throws RemoteException {
		this.controller.setMessage(message);
	}

	/*
	 * Logout from Server and unbind from registry
	 */
	@Override
	public void logout(String user) throws RemoteException, NotBoundException {
		this.registry.unbind(this.name);
		this.cServer.logout(this.name);
	}

}
