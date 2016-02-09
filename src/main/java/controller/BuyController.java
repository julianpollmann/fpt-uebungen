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
	private Pair<String, String> loginResult;
	private Order order;
	private Order orderResult;
	private TCPClient tcpclient;

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
			tcpclient = new TCPClient(loginResult, order, this);
			tcpclient.openConnection();
		}
	}

	public void setResult(Order order) {
		this.orderResult = order;
		System.out.println(this.orderResult.getSum());
		passDataModel();
	}

	private void passDataModel() {
		for(Product remoteProd : this.orderResult) {
			Product localProd = this.model.findProductByName(remoteProd.getName());
			localProd.setQuantity(localProd.getQuantity() - remoteProd.getQuantity());
		}

	}
}
