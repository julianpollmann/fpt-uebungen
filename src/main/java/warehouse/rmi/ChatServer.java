package warehouse.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatService {

	private ArrayList<String> userList;
	private String user;
	private String message;

	public ChatServer() throws RemoteException {
		super();
		userList = new ArrayList<String>();
	}

	@Override
	public void login(String user) throws RemoteException {
		userList.add(user);
	}

	@Override
	public void logout(String user) throws RemoteException {
		userList.remove(user);
	}

	@Override
	public void send(String message) throws RemoteException {
		System.out.println(message);
	}

	@Override
	public List<String> getUserList() throws RemoteException {
		return this.userList;
	}

}
