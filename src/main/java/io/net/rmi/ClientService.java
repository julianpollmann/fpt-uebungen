package io.net.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {

	public void send(String string) throws RemoteException;

	public String getName() throws RemoteException;

}
