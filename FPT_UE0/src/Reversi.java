import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Reversi extends Application{

	final static int LAENGE = 2;
	final static int BREITE = 2;
	Button[][] butt = new Button[LAENGE][BREITE];
	Button resetButt = new Button("Reset");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
	
		BorderPane bp = new BorderPane();
		HBox hbox = new HBox();
		GridPane layout = new GridPane();
		
		layout.setHgap(2);
		layout.setVgap(2);
		
		bp.setBottom(hbox);
		bp.setCenter(layout);
		
		Scene scene = new Scene(bp, 500, 500);
		
		for (int x=0; x<LAENGE; x++) {
			for (int y=0; y<BREITE; y++) {
				butt[x][y] = new Button();
				butt[x][y].setId(x + ":" + y);
				butt[x][y].setStyle("-fx-background-color: #0000FF");
				butt[x][y].setPrefSize(100, 100);
				
				butt[x][y].setOnAction(e -> {
					Button refButt = (Button) e.getSource();
					farbWechsel(refButt);
				});
				layout.add(butt[x][y], y, x);
				System.out.println(butt[x][y].getId());
			}
		}
		
		resetButt.setOnAction(e -> reset());
		
		hbox.getChildren().add(resetButt);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void reset(){
		for (Button but[] : butt) {
			for (Button bu : but) {
				bu.setStyle("-fx-background-color: #0000FF");
			}
		}
	}
	
	public void farbWechsel(Button hans) {
		if (hans.getStyle() == "-fx-background-color: #0000FF") {
			hans.setStyle("-fx-background-color: #FFFF00");
		} else {
			hans.setStyle("-fx-background-color: #0000FF");
		}

		int xKoordinate = Integer.parseInt(""+hans.getId().charAt(0));
		int yKoordinate = Integer.parseInt(""+hans.getId().charAt(2));
		System.out.println("" + xKoordinate + " : " + yKoordinate);

		if ((xKoordinate-1) >= 0){
			if (butt[xKoordinate-1][yKoordinate].getStyle() == "-fx-background-color: #0000FF") {
				butt[xKoordinate-1][yKoordinate].setStyle("-fx-background-color: #FFFF00");
			} else {
				butt[xKoordinate-1][yKoordinate].setStyle("-fx-background-color: #0000FF");
			}
		}
		if ((xKoordinate+1) < BREITE){
			if (butt[xKoordinate+1][yKoordinate].getStyle() == "-fx-background-color: #0000FF") {
				butt[xKoordinate+1][yKoordinate].setStyle("-fx-background-color: #FFFF00");
			} else {
				butt[xKoordinate+1][yKoordinate].setStyle("-fx-background-color: #0000FF");
			}
		}
		if ((yKoordinate-1) >= 0){
			if (butt[xKoordinate][yKoordinate-1].getStyle() == "-fx-background-color: #0000FF") {
				butt[xKoordinate][yKoordinate-1].setStyle("-fx-background-color: #FFFF00");
			} else {
				butt[xKoordinate][yKoordinate-1].setStyle("-fx-background-color: #0000FF");
			}
		}
		if ((yKoordinate+1) < LAENGE){
			if (butt[xKoordinate][yKoordinate+1].getStyle() == "-fx-background-color: #0000FF") {
				butt[xKoordinate][yKoordinate+1].setStyle("-fx-background-color: #FFFF00");
			} else {
				butt[xKoordinate][yKoordinate+1].setStyle("-fx-background-color: #0000FF");
			}
		}
		
		boolean gew = true;
		
		for (Button but[] : butt) {
			for (Button bu : but) {
				if (bu.getStyle().equals("-fx-background-color: #0000FF")) {
					gew = false;
				}
			}
		}
		
		if(gew) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gewonnen!");
			alert.setContentText("Gewonnen jaaaaaaaaaaaAAAAA!");
			alert.setHeaderText("");
			alert.showAndWait();
		}
		
	}
}
