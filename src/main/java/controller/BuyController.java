package controller;

import fpt.com.Order;
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
	private Order order;

	public BuyController(ModelShop model, ViewCustomer view, Order order) {
		this.model = model;
		this.view = view;
		this.order = order;
	}

	@Override
	public void handle(Event event) {

		/*
		 * Check if product is selected
		 */
		if(this.order == null) {
			this.view.openAlert();
		} else {
			loginResult = this.view.openLoginDialog();
			TCPClient tcpclient = new TCPClient(loginResult, order);
			tcpclient.openConnection();
		}
	}

//	private void startClient() {
//		OutgoingThread ot = new OutgoingThread(loginResult, prod);
//		ot.run();
//	}

}
