package controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.ModelShop;
import model.Product;
import view.ViewShop;

public class ListChangeController implements ListChangeListener{

	ModelShop m;
	ViewShop v;

	public ListChangeController(ModelShop m, ViewShop v) {
		this.m = m;
		this.v = v;
	}


	@Override
	public void onChanged(Change c) {


		ObservableList<fpt.com.Product> target = FXCollections.observableList(m.getProductList());


	v.getProductTable().setItems(target);




	}



}
