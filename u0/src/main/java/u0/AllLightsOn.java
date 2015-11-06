package u0;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AllLightsOn extends Application {

	public AllLightsOn() {
		// Blahblah
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// Layoutelemente erstellen
			BorderPane root = new BorderPane();
			GridPane gp = new GridPane();
			HBox hbox = new HBox();

			Scene scene = new Scene(root, 500, 500);

			// Buttons Menüleiste
			Button btnStart = new Button("Start");
			Button btnReset = new Button("Reset");
			hbox.getChildren().addAll(btnStart, btnReset);

			/*
			 * Spielfeld erzeugen mit
			 * Spalten (gridWidth) = 5
			 * Zeilen (gridHeight) = 5
			 */
			int gridWidth = 5;
			int gridHeight = 5;
//			Button buttonGrid[][] = new Button[gridWidth][gridHeight];
			ToggleButton buttonGrid[][] = new ToggleButton[gridWidth][gridHeight];

			for(int i = 0; i < gridWidth; i++) {
				for(int j = 0; j < gridHeight; j++) {
					buttonGrid[i][j] = new ToggleButton("" + i + "," + "" + j);
					buttonGrid[i][j].setPrefSize(100,  100);
					buttonGrid[i][j].setId(i + ":" + j);

					// ButtonHandler
					buttonGrid[i][j].setOnAction(e->{
						ToggleButton btnAction = (ToggleButton)(e.getSource());
						String btnActionId = btnAction.getId();
						System.out.println(btnActionId);

						// TODO: Status bei umgebenen Buttons setzen
						// https://stackoverflow.com/questions/8065571/change-state-of-toggle-button-from-another-button
//						buttonGrid[i-1][j].setSelected(true);
//						buttonGrid[i+1][j].setSelected(true);
//						buttonGrid[i][j-1].setSelected(true);
//						buttonGrid[i][j+1].setSelected(true);



					});

					gp.add(buttonGrid[i][j], i%gridWidth, j%gridHeight);
				}
			}


			// Layoutelemente zu root Element hinzufügen
			root.setTop(hbox);
			root.setCenter(gp);

			// Scene hinzufügen
			primaryStage.setTitle("Alle Lampen an. Ihr Trinker.");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: Tolles Exceptionhandling hier
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
