package controller;

import java.io.IOException;

import io.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ModelShop;
import view.ViewShop;


public class SaveXmlController implements EventHandler {

	private ModelShop model;
	private ViewShop view;

	private String strategy;
	private SerializationStrategy serialization;

	public SaveXmlController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;
	}

	/*
	 * Holt Strategie als String aus dem View
	 * Prüft durch switch/case, welche Strategie gewählt wurde
	 * TODO: Refaktorisieren mit ObservableArrayList oder Index, nicht String
	 */
	@Override
	public void handle(Event arg0) {
		strategy = view.getStrategy();
		switch(strategy) {
			case "Binäre Serialisierung":
				serialization = new SerializationStrategy(new BinaryStrategy());
				try {
					serialization.executeWriteStrategy();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "XML Serialisierung mit Beans":
				serialization = new SerializationStrategy(new XMLStrategy());
				try {
					serialization.executeWriteStrategy();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "XStream-Serialisierung":
				serialization = new SerializationStrategy(new XStreamStrategy());
				try {
					serialization.executeWriteStrategy();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
}