package io.net.rmi;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {

	public void send(String message) throws RemoteException;

	public String getName() throws RemoteException;

	public void setMessage(String message) throws RemoteException;

	public void logout(String user) throws RemoteException, NotBoundException;

}
