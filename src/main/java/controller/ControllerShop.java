package controller;

import javafx.event.ActionEvent;
import model.ModelShop;
import view.ViewShop;

public class ControllerShop {

	public void link(ModelShop model, ViewShop view) {


		model.addListener(new ListChangeController(model,view));
		view.getAddProd().addEventHandler(ActionEvent.ACTION, new AddButtonController(model,view));


	}

}
