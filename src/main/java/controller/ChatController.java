package controller;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import io.net.rmi.ChatClient;
import io.net.rmi.ClientService;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ChatController implements EventHandler {

	private ClientService client;
//	private ViewChat view;

	public ChatController() {
		try {
			client = new ChatClient(this, "Horst");
		} catch (RemoteException | NotBoundException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(Event event) {
//		client.send(view.getChatInput());
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
//		this.view.setMessage();
	}

}
