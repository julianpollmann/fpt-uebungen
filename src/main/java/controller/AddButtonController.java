package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.IDGenerator;
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
		String name = v.getNameInput().getText();
		double price = Double.parseDouble(v.getPriceInput().getText());
		int quant = Integer.parseInt(v.getQuantityInput().getText());
		long id = model.IDGenerator.generateID(m.getProductList());
		Product target = new Product(name, price, quant, id);
		m.add(target);
	}

}
