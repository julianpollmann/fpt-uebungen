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

	public ChatClient(ChatController controller, String name) throws RemoteException, NotBoundException, AlreadyBoundException {
		this.registry = LocateRegistry.getRegistry();
		this.cServer = (ChatService) registry.lookup("chatserver");

		this.controller = controller;
		this.name = name;

		this.registry.bind(this.name, this);
		this.cServer.login(this.name);
	}

	@Override
	public void send(String message) throws RemoteException {
		cServer.send(message);
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public String setMessage(String message) throws RemoteException {
		return this.controller.getMessage(message);
	}

}
