package view;

import fpt.com.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ViewShop extends BorderPane {

	private ToolBar tbar;
	private Label heading;
	private Button addProd;
	private Button delProd;
	private SimpleStringProperty addProdText;
	private SimpleStringProperty delProdText;
//	private ListView<String> products;

	private TableView<Product> prodTable;
	private TableColumn<Product, String> prodName;
	private TableColumn<Product, Number> prodPrice;
	private TableColumn<Product, Number> prodQuantity;

	public ViewShop() {

//		products = new ListView<String>();
		heading = new Label("Hardwareshop");
        heading.setFont(new Font("Arial", 20));
		initTooblar();
		setTableView();

		setTop(heading);
		setCenter(prodTable);
		setBottom(tbar);
	}

	private void initTooblar() {
		tbar = new ToolBar();

		// Textproperty für Buttons
		addProdText = new SimpleStringProperty("Neues Produkt");
		delProdText = new SimpleStringProperty("Produkt löschen");

		// Buttons erzeugen + binden
		addProd = new Button();
		delProd = new Button();
		addProd.textProperty().bind(addProdText);
		delProd.textProperty().bind(delProdText);

		tbar.getItems().addAll(addProd, delProd);
	}

	private void setTableView() {
		// Tabelle erzeugen
		prodTable = new TableView<Product>();

		// Erste Spalte + Value als Property setzen
		prodName = new TableColumn<Product, String>("Produktname");
		prodName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

		// Zweite Spalte
		prodPrice = new TableColumn<Product, Number>("Preis");
		prodPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

		// Dritte Spalte
		prodQuantity = new TableColumn<Product, Number>("Anzahl");
		prodQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());

		prodTable.getColumns().addAll(prodName, prodPrice, prodQuantity);
	}

	// Tabelle an Controller zurückgeben
 	public TableView<Product> getProductTable() {
		return prodTable;
	}

	// Für Verwendung des ListViews
//	private ListCell<String> setListView() {
//		products.setCellFactory(c -> {
//
//			ListCell<String> cell = new ListCell<String>() {
//				@Override
//				protected void updateItem(String myObject, boolean b) {
//					super.updateItem(myObject, myObject == null || b);
//					if (myObject != null) {
//						setText("..." + myObject + "...");
//					} else {
//						// wichtig da sonst der text stehen bleibt!
//						setText("");
//					}
//				}
//
//			};
//			return cell;
//		});
//		return null;
//	}
}
