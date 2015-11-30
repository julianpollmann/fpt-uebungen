package controller;

import model.ModelShop;
import view.ViewShop;

public class ControllerShop {

	public void link(ModelShop model, ViewShop view) {
		/*
		 * Table aus der View holen +
		 * Produktliste aus dem Model als
		 * Daten übergeben
		 */
		view.getProductTable().setItems(model.getProductList());
	}

}
