package view;

import java.util.Optional;

import fpt.com.Product;
import io.net.udp.UDPClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.net.*;


public class ViewCustomer extends BorderPane {

	private Label heading;
	private Button addProd, history, buy;
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
	ViewHistory view3;
	private ViewLogin login;
	private Optional<Pair<String, String>> loginResult;
	private Product prod;
	private Text time;

	private UDPClient udpclient;

	// TODO: Rebuild UI using Threading:
	// http://blog.axxg.de/javafx-ui-thread-update/
	public ViewCustomer() {

//		products = new ListView<String>();
		heading = new Label("Ihre Bestellung");
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

		udpclient = new UDPClient(this);
		udpclient.start();

	}

	private void setProductDetails() {

		// Buttons erzeugen + binden
		addProd = new Button("In Warenkorb legen");
		history = new Button("Vorherige Bestellungen");
		history.setOnAction(e -> {
			view3 = new ViewHistory();
			Stage stage3 = new Stage();
			Scene scene3 = new Scene(view3, 800, 800);
			stage3.setTitle("Vorherige Bestellungen");
			stage3.setScene(scene3);
			stage3.show();
		});
		buy = new Button("Kaufen");
		time = new Text();
//		buy.setOnAction(e -> {
//			prod = prodTable.getSelectionModel().getSelectedItem();
//			openLoginDialog();
//		});

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
		vbox.getChildren().addAll(nameLabel, nameInput, priceLabel, priceInput, quantityLabel, quantityInput, addProd, history, buy, time);

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

 	public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
 		addProd.addEventHandler(ActionEvent.ACTION, eventHandler);
 	}

	public String getInputPaneText() {
		// TODO: Array zurückgeben für alle Parameter
		return nameInput.getText();
	}

	public Pair<String, String> openLoginDialog() {
		login = new ViewLogin();
		loginResult = login.showAndWait();

//		loginResult.ifPresent(usernamePassword -> {
//	    	System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//		});
		return loginResult.get();
	}

	public void openAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warnung");
		alert.setHeaderText("Kein Produkt gewählt.");
		alert.setContentText("Bitte wählen Sie ein Produkt.");

		alert.showAndWait();
	}

	public Button getBuy() {
		return buy;
	}

	public Button getAddToCart() {
		return addProd;
	}

	public void setTime(String time) {
		this.time.setText(time);
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
