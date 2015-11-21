package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.ModelShop;
import view.ViewShop;
import controller.ControllerShop;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			ModelShop model = new ModelShop();
			ViewShop view = new ViewShop();
			ControllerShop controller = new ControllerShop();

			controller.link(model, view);

			Scene scene = new Scene(view, 800, 800);

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
