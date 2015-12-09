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

	private ModelShop m;
	private ViewShop v;
	private String name, msg;
	private double price;
	private int quant;
	private long id;
	private Product target;

	public AddButtonController(ModelShop model, ViewShop view) {
		this.m = model;
		this.v = view;

		this.name = new String();
		this.price = 0;
		this.quant = 0;
		this.id = 0;
		Product target = null;
	}

	@Override
	public void handle(Event arg0) {
		try {
			name = v.getNameInput().getText();
			price = v.getPriceInput();
			quant = v.getQuantityInput();
			id = IDGenerator.generateID(m.getProductList());
			target = new Product(id, name, price, quant);
			m.add(target);
		} catch (Exception e) { //Falls IDs ueberschritten werden poppt ein Dialog auf
			String msg = e.getMessage();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Fehler");
			alert.setHeaderText(null);
			alert.setContentText(msg);
			alert.showAndWait();
		}
	}

}
