package view;

import fpt.com.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.ModelShop;

public class ViewShop extends BorderPane {

	private ToolBar tbar;
	private Label heading;
	private Button addProd;
	private Button delProd;
	private SimpleStringProperty addProdText;
	private SimpleStringProperty delProdText;
	private ListView<Product> products;

	public ViewShop() {

		products = new ListView<Product>();
		heading = new Label("Hardwareshop");
        heading.setFont(new Font("Arial", 20));
		initTooblar();

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

	public void setListView() {
//		products.setCellFactory(e -> {
//			ListCell<Product> cell = new ListCell<Product>() {
////				@Override
////				protected void updateItem(Product myObject, boolean b) {
////					super.updateItem(myObject, myObject == null || b);
//////					if (myObject != null) {
//////						setText(myObject.getName() + " | " + myObject.getPrice()+ " €  | " + myObject.getQuantity());
//////					} else {
//////					}
////				}
//			};
//		}
//	public void bindData(ModelShop model) {	products.setItems(model}
//	BindData wird im controller aufgerufen (view.bindData)

	}











}
