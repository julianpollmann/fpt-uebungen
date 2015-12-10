package controller;

import java.io.IOException;

import fpt.com.ProductList;
import io.*;
import model.ModelShop;
import fpt.com.Product;
import view.ViewShop;
import javafx.event.Event;
import javafx.event.EventHandler;


public class SaveController implements EventHandler {

	private ModelShop model;
	private ViewShop view;
    private SerializationStrategy[] serialization;
    private int x;
	Product product;
	ProductList productList;

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
       		// Model nutzen + alle Produkte serialisieren
       		ProductList productList = model.getProductList();

        	for (Product product: productList) {
        		serialization[x].executeWriteStrategy(product);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}