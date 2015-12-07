package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import fpt.com.Product;
import view.ViewShop;

public class RemoveButtonController implements EventHandler {

	ModelShop model;
	ViewShop view;

	public RemoveButtonController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle(Event arg0) {
		Product prod = view.getProductTable().getSelectionModel().getSelectedItem();
		model.remove(prod);
		view.getProductTable().getSelectionModel().clearSelection();
	}
}