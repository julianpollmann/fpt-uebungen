package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import model.Product;
import view.ViewShop;

public class RemoveButtonController implements EventHandler {

	ModelShop m;
	ViewShop v;

	public RemoveButtonController(ModelShop model, ViewShop view) {

		this.m = model;
		this.v = view;
	}

	@Override
	public void handle(Event arg0) {

		// TODO: Handling delete button
//		testList = v.getProductTable().getR;
//		System.out.println(testList.get(0).getName());
//		System.out.println(delPro.getPrice());
//		System.out.println(delPro.getName());
//		m.remove(delPro);
		v.getProductTable().getSelectionModel().clearSelection();

	}
}