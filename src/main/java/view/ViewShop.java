package view;

import fpt.com.Product;
import javafx.beans.property.SimpleStringProperty;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewShop extends BorderPane {

	private Label heading;
	private Button addProd;
	private Button delProd;
	private SimpleStringProperty addProdText;
	private SimpleStringProperty delProdText;
//	private ListView<String> products;
	private VBox vbox;
	private Label nameLabel;
	private TextField nameInput;
	private Label priceLabel;
	private TextField priceInput;
	private Label quantityLabel;
	private TextField quantityInput;

	private String nameString;
	private String priceString;
	private String quantityString;

	private TableView<Product> prodTable;
	private TableColumn<Product, String> prodName;
	private TableColumn<Product, Number> prodPrice;
	private TableColumn<Product, Number> prodQuantity;

	public ViewShop() {

//		products = new ListView<String>();
		heading = new Label("Hardwareshop");
        heading.setFont(new Font("Arial", 20));
        heading.setPadding(new Insets(10, 0, 10, 10));

        nameString = "Produktname";
        priceString = "Preis";
        quantityString = "Anzahl";

        setProductDetails();
		setTableView();

		setTop(heading);
		setCenter(prodTable);
		setRight(vbox);
//		setBottom(stackp);
	}

	private void setProductDetails() {
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
		
		System.out.println();
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
