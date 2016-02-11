package controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import io.net.rmi.ClientService;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ChatLogoutController implements EventHandler {

	private ChatController controller;
	private ClientService client;

	public ChatLogoutController(ChatController controller) {
		this.controller = controller;
	}

	@Override
	public void handle(Event event) {
		client = this.controller.getChatClient();
		try {
			client.logout(client.getName());
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
