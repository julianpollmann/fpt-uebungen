package controller;

import javafx.event.ActionEvent;
import model.ModelShop;
import view.ViewShop;

public class ControllerShop {

	public void link(ModelShop model, ViewShop view) {

		model.addListener(new ListChangeController(model,view));
		view.getAddProd().addEventHandler(ActionEvent.ACTION, new AddButtonController(model,view));
		view.getDelProd().addEventHandler(ActionEvent.ACTION, new RemoveButtonController(model,view));
		view.getSaveXML().addEventHandler(ActionEvent.ACTION, new SaveController(model,view));
		view.getLoadXML().addEventHandler(ActionEvent.ACTION, new LoadController(model,view));

	}
}
