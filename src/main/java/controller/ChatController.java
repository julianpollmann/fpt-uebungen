package controller;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import io.net.rmi.ChatClient;
import io.net.rmi.ClientService;
import javafx.event.Event;
import javafx.event.EventHandler;
import view.ViewChat;

public class ChatController implements EventHandler {

	private ClientService client;
	private ViewChat view;
	private String user;

	public ChatController(ViewChat view) {
		this.view = view;
		this.user = this.view.getUserName();

		try {
			client = new ChatClient(this, this.user);
		} catch (RemoteException | NotBoundException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(Event event) {
		try {
			client.send(this.view.getInputChat());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// temp, this goes in handle
	public void sendMessage(String message) {
		try {
			client.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setMessage(String message) {
		System.out.println("ChatController Message: " + message);
		this.view.addMessage(message);
	}

}
