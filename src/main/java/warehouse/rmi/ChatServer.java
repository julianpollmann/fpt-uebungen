package warehouse.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatService {

	public ChatServer() throws RemoteException {

	}

	@Override
	public void login(String string) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout(String string) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(String string) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
