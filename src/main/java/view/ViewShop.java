package view;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ViewShop extends BorderPane {

	ToolBar tbar;
	Label heading;
	TableView table;
	Button addProd;
	Button delProd;

	public ViewShop() {

		heading = new Label("Hardwareshop");
        heading.setFont(new Font("Arial", 20));
		initTable();
		initTooblar();

		setTop(heading);
		setCenter(table);
		setBottom(tbar);
	}

	private void initTooblar() {
		tbar = new ToolBar();

		addProd = new Button("Neues Produkt");
		delProd = new Button("Produkt löschen");

		tbar.getItems().addAll(addProd, delProd);
	}

	private void initTable() {
		table = new TableView();
		table.setEditable(true);

		// Spalten erzeugen
		TableColumn pId = new TableColumn("id");
		TableColumn pName = new TableColumn("Produkt");
		TableColumn pPrice = new TableColumn("Preis");
		TableColumn pQuantity = new TableColumn("Stückzahl");

		table.getColumns().addAll(pId, pName, pPrice, pQuantity);

		// Produkte hinzufügen, hier müsste irgendwas an Liste, Array oder sonstwas rein
//		table.setItems();
	}

}
