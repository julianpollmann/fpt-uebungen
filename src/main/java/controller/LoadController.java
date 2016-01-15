package controller;

import java.io.IOException;
import java.sql.SQLException;

import io.*;
import io.db.JDBCConnector;
import io.db.JDBCStrategy;
import io.db.OpenJPA;
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

    private SerializationStrategy[] strategy;

    private fpt.com.SerializableStrategy strat;



    private JDBCStrategy jdbc;
    private int x;
    private int y;

	public LoadController(ModelShop model, ViewShop view) {
		this.model = model;
		this.view = view;

		strategy = new SerializationStrategy[3];
		strategy[0] = new SerializationStrategy(new BinaryStrategy());
		strategy[1] = new SerializationStrategy(new XMLStrategy());
		strategy[2] = new SerializationStrategy(new XStreamStrategy());
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
			strat = new JDBCStrategy();
			x = 3;
			break;
		case "OpenJPA-DB-Verbindung":
			x = 4;
			break;
		}
		try {
			if (path != null){
				productList = strategy[x].executeReadStrategy(path);
				model.getProductList().clear();
				for(fpt.com.Product p : productList) {
					model.add(p);
				}
			}
			else if (x == 3) {
				JDBCConnector jdbc = new JDBCConnector();
				for (fpt.com.Product p : jdbc.read()){
					model.add(p);
				}
			}
			else if (x == 4) {
				OpenJPA ojpa = new OpenJPA();
				for (fpt.com.Product p : ojpa.read()){
					model.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}