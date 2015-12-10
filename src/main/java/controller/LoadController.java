package controller;

import java.io.IOException;

import io.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import model.Product;
import fpt.com.ProductList;
import view.ViewShop;


public class LoadController implements EventHandler {

	private ModelShop model;
	private ViewShop view;
	private String path;
	private ProductList productList;

    private SerializationStrategy[] serialization;
    private int x;

	public LoadController(ModelShop model, ViewShop view) {
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
			path = "ProdukteBinaer.ser";
			break;
		case "XML Serialisierung mit Beans":
			x = 1;
			path = "products.xml";
			break;
		case "XStream-Serialisierung":
			x = 2;
			path = "produktliste.xml";
			break;
		}
		try {
			productList = serialization[x].executeReadStrategy(path);
			model.getProductList().clear();
			for(fpt.com.Product p : productList) {
				model.add(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}}