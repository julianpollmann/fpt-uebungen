package warehouse.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {

	public void login(String user) throws RemoteException;

	public void logout(String user) throws RemoteException;

	public void send(String message) throws RemoteException;

	public List<String> getUserList() throws RemoteException;
}
