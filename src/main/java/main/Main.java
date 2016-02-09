package main;
import controller.ControllerShop;
import io.net.rmi.ChatClient;
import io.net.rmi.ClientService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelShop;
import view.ViewCustomer;
import view.ViewHistory;
import view.ViewShop;

public class Main extends Application {

	public ModelShop model;
	public ViewShop view;
	public ViewCustomer view2;
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

			ClientService client = new ChatClient("Horst");

			// Customerview in zweitem Fenster
//			view2 = new ViewCustomer();
//
//			Stage stage2 = new Stage();
//			Scene scene2 = new Scene(view2, 800, 500);
//			stage2.setTitle("Ihre Bestellung");
//			stage2.setScene(scene2);
//			stage2.show();

			// Historyview in drittem Fenster - soll sich nachher erst auf Buttondruck öffnen

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
