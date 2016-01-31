package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ViewLogin extends Dialog {

	private GridPane grid;
	private TextField username;
	private PasswordField password;
	private ButtonType loginButtonType;
	private Node loginButton;

	public ViewLogin() {
		setTitle("Login");
		setHeaderText("Bitte einloggen");

		setLoginForm();
	}

	/*
	 * Creates Login Form
	 */
	private void setLoginForm() {
		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// User/PW fields
		username = new TextField();
		username.setPromptText("Nutzername");
		password = new PasswordField();
		password.setPromptText("Passwort");

		grid.add(new Label("Nutzername:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Passwort:"), 0, 1);
		grid.add(password, 1, 1);

		// Add Login/Cancel Button
		loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// disable Button, enable when there's input
		Node loginButton = getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		// set input elements
		getDialogPane().setContent(grid);

		// ResultConverter for Button Handling
		setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		// Focus input
		Platform.runLater(() -> username.requestFocus());
	}
}