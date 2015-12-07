package view;

import fpt.com.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewShop extends BorderPane {

	private Label heading, nameLabel, priceLabel, quantityLabel, stratLabel;
	private Button addProd, delProd, loadStrat, safeStrat, loadXML, saveXML;
	private SimpleStringProperty addProdText, delProdText, ladenText, speichernText;
	private HBox hbox;
	private VBox vbox;
	private TextField nameInput, priceInput, quantityInput;
	private ObservableList<String> strategies;
	private ChoiceBox<String> strategy;
	private String nameString, priceString, quantityString;

//	private ListView<String> products;
	private TableView<Product> prodTable;
	private TableColumn<Product, String> prodName;
	private TableColumn<Product, Number> prodPrice, prodQuantity;

	public ViewShop() {

//		products = new ListView<String>();
		heading = new Label("Hardwareshop");
        heading.setFont(new Font("Arial", 20));
        heading.setPadding(new Insets(10, 0, 10, 10));

        nameString = "Produktname";
        priceString = "Preis";
        quantityString = "Anzahl";

        setProductManagement();
		setTableView();
		setSerialization();

		setTop(heading);
		setCenter(prodTable);
		setRight(vbox);
		setBottom(hbox);
	}

	private void setSerialization() {

		strategies = FXCollections.observableArrayList();
		strategies.addAll(
				"Binäre Serialisierung",
				"XML Serialisierung mit Beans",
				"XStream-Serialisierung");
		strategy = new ChoiceBox<>(strategies);
		strategy.setValue("Binäre Serialisierung");

		ladenText = new SimpleStringProperty("Laden");
		speichernText = new SimpleStringProperty("Speichern");

		stratLabel = new Label("Strategie:");
		loadXML = new Button("Laden");
	    saveXML = new Button("Speichern");

	    loadXML.textProperty().bind(ladenText);
	    saveXML.textProperty().bind(speichernText);

		hbox = new HBox(4);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.getChildren().addAll(stratLabel, strategy, loadXML, saveXML);
	}

	// TODO: Rückgabewert anpassen
	public String getStrategy() {
		return strategy.getValue();
	}

	private void setProductManagement() {
		// Textproperty für Buttons
		addProdText = new SimpleStringProperty("Produkt hinzufügen");
		delProdText = new SimpleStringProperty("Produkt löschen");

		// Buttons erzeugen + binden
		addProd = new Button();
		delProd = new Button();
		addProd.textProperty().bind(addProdText);
		delProd.textProperty().bind(delProdText);

		// Name Label + Textfeld
		nameLabel = new Label(nameString);
		nameInput = new TextField();

		// Preis Label + Textfeld
		priceLabel = new Label(priceString);
		priceInput = new TextField();

		// Preis Label + Textfeld
		quantityLabel = new Label(quantityString);
		quantityInput = new TextField();


		// VBox erzeugen, stylen + Elemente zuweisen
		vbox = new VBox(6);
		vbox.setPrefWidth(200);
		vbox.setPadding(new Insets(0, 10, 0, 10));
		vbox.getChildren().addAll(nameLabel, nameInput, priceLabel, priceInput, quantityLabel, quantityInput, addProd, delProd);
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

		prodTable.getColumns().addAll(prodName, prodPrice, prodQuantity);
	}

	// Tabelle an Controller zurückgeben
 	public TableView<Product> getProductTable() {
		return prodTable;
	}

	public String getInputPaneText() {
		// TODO: Array zurückgeben für alle Parameter
		return nameInput.getText();
	}

	public Button getAddProd() {
		return addProd;
	}

	public void setAddProd(Button addProd) {
		this.addProd = addProd;
	}

	public Button getDelProd() {
		return delProd;
	}

	public void setDelProd(Button delProd) {
		this.delProd = delProd;
	}

	public TextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(TextField nameInput) {
		this.nameInput = nameInput;
	}

	public TextField getPriceInput() {
		return priceInput;
	}

	public void setPriceInput(TextField priceInput) {
		this.priceInput = priceInput;
	}

	public TextField getQuantityInput() {
		return quantityInput;
	}

	public void setQuantityInput(TextField quantityInput) {
		this.quantityInput = quantityInput;
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
