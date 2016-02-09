package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import view.ViewCustomer;
import fpt.com.Product;
import fpt.com.Order;

public class ShoppingCartController implements EventHandler {

	private ModelShop model;
	private ViewCustomer view;
	private Product product;
	private Order order;

	public ShoppingCartController(ModelShop model, ViewCustomer view, Order order) {
		this.model = model;
		this.view = view;
		this.order = order;
	}

	@Override
	public void handle(Event event) {
		product = view.getProductTable().getSelectionModel().getSelectedItem();

		this.order.add(product);

		System.out.println(order.size());
		System.out.println(order.getSum());

	}

}
