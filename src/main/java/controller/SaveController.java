package controller;

import java.io.IOException;

import fpt.com.ProductList;
import fpt.com.db.AbstractDatabaseStrategy;
import io.*;
import io.db.JDBCConnector;
import io.db.OpenJPA;
import model.ModelShop;
import fpt.com.Product;
import view.ViewShop;
import javafx.event.Event;
import javafx.event.EventHandler;


public class SaveController implements EventHandler {

	private ModelShop model;
	private ViewShop view;
    private SerializationStrategy[] serialization;
    private AbstractDatabaseStrategy[] databaseStrategy; //wird noch nicht verwendet
    private JDBCConnector jdbc;
    private OpenJPA ojpa;
	Product product;
	ProductList productList;
	private String path;
	private int x;
	private int y;

	public SaveController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;

		serialization = new SerializationStrategy[3];
		serialization[0] = new SerializationStrategy(new BinaryStrategy());
		serialization[1] = new SerializationStrategy(new XMLStrategy());
		serialization[2] = new SerializationStrategy(new XStreamStrategy());
		//JDBCConnector jDBC = new JDBCConnector();

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
			case "JDBC-DB-Verbindung":
				x = 3;
				break;
			case "OpenJPA-DB-Verbindung":
				x = 4;
				break;
		}
        try {

        	//ProductList productList = model.getProductList();

        	if (path != null) {
            	serialization[x].executeWriteStrategy(model.getProductList(), path);
        	}
        	else if (x == 3) {
        		jdbc = new JDBCConnector();
        		for (Product p : model.getProductList()) {
        			jdbc.insert((model.Product) p);
        		}
        	} else if (x == 4) {
        		ojpa = new OpenJPA();
           		for (Product p : model.getProductList()) {
        			jdbc.insert((model.Product) p);
           		}
        	}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}