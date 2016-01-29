package controller;

import fpt.com.Product;
import io.OutgoingThread;
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
		loginResult = this.view.openLoginDialog();
		prod = view.getProductTable().getSelectionModel().getSelectedItem();

		OutgoingThread ot = new OutgoingThread(loginResult, prod);
		ot.run();

	}

}
