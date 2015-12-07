package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
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
		try {
			String name = v.getNameInput().getText();
			double price = Double.parseDouble(v.getPriceInput().getText());
			int quant = Integer.parseInt(v.getQuantityInput().getText());
			long id;
			id = model.IDGenerator.generateID(m.getProductList());
			Product target = new Product(id, name, price, quant);
			m.add(target);
		} catch (Exception e) { //Falls IDs ueberschritten werden poppt ein Dialog auf
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ID Overflow-Warnung");
			alert.setHeaderText(null);
			alert.setContentText("Eintrag nicht möglich! Es wurden bereits sämtliche IDs vergeben.");
			alert.showAndWait();
		}
		v.getNameInput().setText("");
		v.getPriceInput().setText("");
		v.getQuantityInput().setText("");
	}

}
