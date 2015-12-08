package controller;

import java.io.IOException;

import io.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import view.ViewShop;


public class SaveXmlController implements EventHandler {

	ModelShop model;
	ViewShop view;

	private String strategy;
	private SerializationStrategy serialization;

	public SaveXmlController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle(Event arg0) {
		// TODO: Refaktorisieren mit ObservableArrayList?!
		strategy = view.getStrategy();
		switch(strategy) {
			case "Binäre Serialisierung":
				serialization = new SerializationStrategy(new BinaryStrategy());
				try {
					serialization.executeReadStrategy();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "XML Serialisierung mit Beans":
//				serialization = new SerializationStrategy(new XMLStrategy());
				break;
			case "XStream-Serialisierung":
				serialization = new SerializationStrategy(new XStreamStrategy());
				try {
					serialization.executeReadStrategy();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	}
}