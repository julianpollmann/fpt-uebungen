package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import model.Product;
import view.ViewShop;

public class AddButtonController implements EventHandler {

	ModelShop m;
	ViewShop v;

	public AddButtonController(ModelShop model, ViewShop view) {

		this.m = model;
		this.v = view;
	}

	@Override
	public void handle(Event arg0) {

		long id = m.setId();
		String name = v.getNameInput().getText();
		double price = Double.parseDouble(v.getPriceInput().getText());
		int quant = Integer.parseInt(v.getQuantityInput().getText());
		Product target = new Product(id, name, price, quant);
		m.add(target);



	}

}
