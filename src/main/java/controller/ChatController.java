package controller;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import io.net.rmi.ChatClient;
import io.net.rmi.ClientService;
import javafx.application.Platform;
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

		// Create new Chatclient with username here
		try {
			client = new ChatClient(this, this.user);
		} catch (RemoteException | NotBoundException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Eventhandler for Send Button
	 */
	@Override
	public void handle(Event event) {
		try {
			client.send(this.view.getInputChat());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Update message in view
	 * we need to use runLater
	 */
	public void setMessage(String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				view.getMessages().add(message);
			}

		});
	}

	public ClientService getChatClient() {
		return this.client;
	}

}
