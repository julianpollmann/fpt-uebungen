package controller;

import fpt.com.Product;
import io.net.tcp.TCPClient;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Pair;
import model.ModelShop;
import view.ViewCustomer;

public class BuyController implements EventHandler {

	private ModelShop model;
	private ViewCustomer view;
	private Product prod;
	private Pair<String, String> loginResult;

	public BuyController(ModelShop model, ViewCustomer view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle(Event event) {
		prod = view.getProductTable().getSelectionModel().getSelectedItem();

		/*
		 * Check if product is selected
		 */
		if(prod == null) {
			this.view.openAlert();
		} else {
			loginResult = this.view.openLoginDialog();
			TCPClient tcpclient = new TCPClient(loginResult, prod);
			tcpclient.openConnection();
		}
	}

//	private void startClient() {
//		OutgoingThread ot = new OutgoingThread(loginResult, prod);
//		ot.run();
//	}

}
