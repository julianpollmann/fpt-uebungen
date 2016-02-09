package io.net.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ClientService {

	public ChatClient() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(String string) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
