package io.net.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import warehouse.rmi.ChatService;

public class ChatClient extends UnicastRemoteObject implements ClientService {

	private Registry registry;
	private ChatService cServer;
	private String name;

	public ChatClient(String name) throws RemoteException, NotBoundException, AlreadyBoundException {
		this.registry = LocateRegistry.getRegistry();
		this.cServer = (ChatService) registry.lookup("chatserver");

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

}
