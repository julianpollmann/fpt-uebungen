package controller;

import fpt.com.Order;
import io.net.udp.UDPClient;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelShop;
import view.ViewChat;
import view.ViewChatLogin;
import view.ViewCustomer;
import view.ViewShop;

public class ControllerShop {

	public ViewCustomer view2;
	public ViewChat view3;
	private ViewChatLogin view4;
	private Order order;
//	private UDPClient udpclient;

	public void link(ModelShop model, ViewShop view) {

		view.getProductTable().setItems(model);

		view.getAddProd().addEventHandler(ActionEvent.ACTION, new AddButtonController(model,view));
		view.getDelProd().addEventHandler(ActionEvent.ACTION, new RemoveButtonController(model,view));
		view.getSaveXML().addEventHandler(ActionEvent.ACTION, new SaveController(model,view));
		view.getLoadXML().addEventHandler(ActionEvent.ACTION, new LoadController(model,view));

		// Customerview in zweitem Fenster
		view2 = new ViewCustomer();

		Stage stage2 = new Stage();
		Scene scene2 = new Scene(view2, 800, 500);
		stage2.setTitle("Ihre Bestellung");
		stage2.setScene(scene2);
		stage2.show();
		view2.getProductTable().setItems(model);

		order = new model.Order();
		view2.getBuy().addEventHandler(ActionEvent.ACTION, new BuyController(model, view2, order));
		view2.getAddToCart().addEventHandler(ActionEvent.ACTION, new ShoppingCartController(model, view2, order));


		// Chatfenster
		view3 = new ViewChat();

		Stage stage3 = new Stage();
		Scene scene3 = new Scene(view3, 500, 320);
		stage3.setTitle("Chat");
		stage3.setScene(scene3);
		stage3.show();

		ChatController chatController = new ChatController(view3);
		ChatLogoutController logoutController = new ChatLogoutController(chatController);
		view3.getSendButton().addEventHandler(ActionEvent.ACTION, chatController);
		view3.getLogoutButton().addEventHandler(ActionEvent.ACTION, logoutController);

//		udpclient = new UDPClient(view2);
//		udpclient.start();

	}
}
