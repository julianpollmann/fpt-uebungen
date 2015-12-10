package controller;

import java.io.IOException;

import io.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import model.Product;
import model.ProductList;
import view.ViewShop;


public class LoadController implements EventHandler {

	private ModelShop model;
	private ViewShop view;

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
				break;
			case "XML Serialisierung mit Beans":
				x = 1;
				break;
			case "XStream-Serialisierung":
				x = 2;
				break;
		}
		try {
			serialization[x].executeReadStrategy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}}