package controller;

import java.io.IOException;


import io.*;
import model.ModelShop;
import model.Product;
import view.ViewShop;
import javafx.event.Event;
import javafx.event.EventHandler;


public class SaveController implements EventHandler {

	private ModelShop model;
	private ViewShop view;
    private SerializationStrategy[] serialization;
    private int x;

	public SaveController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;

		serialization = new SerializationStrategy[3];
		serialization[0] = new SerializationStrategy(new BinaryStrategy());
		serialization[1] = new SerializationStrategy(new XMLStrategy());
		serialization[2] = new SerializationStrategy(new XStreamStrategy());

	}

	@Override
	public void handle(Event arg0) {

		switch(view.getStrategy()) {
			case "Binäre Serialisierung":
				x = 0;
				break;
			case "XML Serialisierung mit Beans":
				x = 1;
				break;
			case "XStream-Serialisierung":
				x = 2;
				break;
		}
        try {
        	for (int i = 0; i < view.getProductTable().getItems().size(); i++) {
         		Product target = (Product) view.getProductTable().getItems().get(i);
    			serialization[x].executeWriteStrategy(target);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}