package main;
import controller.ControllerShop;
import io.net.rmi.ChatClient;
import io.net.rmi.ClientService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelShop;
import view.ViewHistory;
import view.ViewShop;

public class Main extends Application {

	public ModelShop model;
	public ViewShop view;
//	public ViewChat view2;
	public ViewHistory view3;
	public ControllerShop controller;

	public Scene scene;
	public Scene scene2;
	public Scene scene3;

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

<<<<<<< HEAD
			ClientService client = new ChatClient("Horst");


=======
>>>>>>> origin/master
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
