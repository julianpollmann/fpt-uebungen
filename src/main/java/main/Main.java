package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.ModelShop;
import view.ViewShop;
import controller.ControllerShop;

public class Main extends Application {

	public ModelShop model;
	public ViewShop view;
	public ControllerShop controller;

	public Scene scene;

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			model = new ModelShop();
			view = new ViewShop();
			controller = new ControllerShop();

			controller.link(model, view);

			scene = new Scene(view, 800, 800);

			primaryStage.setTitle("Hardwareshop");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
