package view;

import fpt.com.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class ViewHistory extends BorderPane {

	private Label heading;
//	private Button addProd;
//	private Button history;
//	private SimpleStringProperty addProdText;
//	private SimpleStringProperty historyText;
//	private ListView<String> products;
	private VBox vbox;
	private Label nameLabel;
	private TextField nameInput;
	private Label priceLabel;
	private TextField priceInput;
	private Label quantityLabel;
	private TextField quantityInput;
	private Label priceOverallLabel;
	private TextField priceOverallInput;

	private String nameString;
	private String priceString;
	private String quantityString;
	private String priceOverallString;

	private TableView<Product> prodTable;
	private TableColumn<Product, String> prodName;
	private TableColumn<Product, Number> prodPrice;
	private TableColumn<Product, Number> prodQuantity;
	private TableColumn<Product, Number> prodPriceOverall;

	public ViewHistory() {

//		products = new ListView<String>();
		heading = new Label("Vorherige Bestellungen");
        heading.setFont(new Font("Arial", 20));
        heading.setPadding(new Insets(10, 0, 10, 10));

        nameString = "Produktname";
        priceString = "Preis";
        quantityString = "Anzahl";
        priceOverallString = "Preis insgesamt";

        setProductDetails();
		setTableView();

		setTop(heading);
		setCenter(prodTable);
//		setBottom(stackp);
	}

	private void setProductDetails() {
		// Textproperty für Buttons
//		addProdText = new SimpleStringProperty("In Warenkorb legen");
//		historyText = new SimpleStringProperty("Vorherige Bestellungen");

		// Buttons erzeugen + binden
//		addProd = new Button();
//		history = new Button();
//		addProd.textProperty().bind(addProdText);
//		history.textProperty().bind(historyText);

		// Name Label + Textfeld
		nameLabel = new Label(nameString);
		nameInput = new TextField();

		// Preis Label + Textfeld
		priceLabel = new Label(priceString);
		priceInput = new TextField();

		// Quantity Label + Textfeld
		quantityLabel = new Label(quantityString);
		quantityInput = new TextField();

		// Gesamtpreis Label + Textfeld
		priceOverallLabel = new Label(priceOverallString);
		priceOverallInput = new TextField();

		// VBox erzeugen, stylen + Elemente zuweisen
		vbox = new VBox(5);
		vbox.setPrefWidth(200);
		vbox.setPadding(new Insets(0, 10, 0, 10));
		vbox.getChildren().addAll(nameLabel, nameInput, priceLabel, priceInput, quantityLabel, quantityInput, priceOverallLabel, priceOverallInput);
	}

	private void setTableView() {
		// Tabelle erzeugen
		prodTable = new TableView<Product>();

		// Erste Spalte + Value als Property setzen
		prodName = new TableColumn<Product, String>(nameString);
		prodName.setMinWidth(400);
		prodName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

		// Zweite Spalte
		prodPrice = new TableColumn<Product, Number>(priceString);
		prodPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

		// Dritte Spalte
		prodQuantity = new TableColumn<Product, Number>(quantityString);
		prodQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());

		// Vierte Spalte
		prodPriceOverall = new TableColumn<Product, Number>(priceOverallString);
		prodPriceOverall.setMinWidth(200);
		//prodPriceOverall.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

		prodTable.getColumns().addAll(prodName, prodPrice, prodQuantity, prodPriceOverall);
	}

	// Tabelle an Controller zurückgeben
 	public TableView<Product> getProductTable() {
		return prodTable;
	}

// 	public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
// 		addProd.addEventHandler(ActionEvent.ACTION, eventHandler);
// 	}

	public String getInputPaneText() {
		// TODO: Array zurückgeben für alle Parameter
		return nameInput.getText();
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
