package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewChat extends BorderPane {

	private Label heading;
	private Button sendButton = new Button("Nachricht senden");
	private Label chatText = new Label("Nachricht:");
//	private Label chatName = new Label("Nickname:");
	private ListView<String> chatView = new ListView();
	private ObservableList<String> messages;
	private TextField inputChat = new TextField();
//	private Button loginButton = new Button("Login");
	private Button logoutButton = new Button("Logout");
	private VBox vbox2;
	private VBox vbox;
//	private HBox hbox;
	private ViewChatLogin loginView;
	private Optional<String> userName;

	public ViewChat() {

//			hbox = new HBox(5);
//			hbox.setPadding(new Insets(0, 0, 0, 0));
//			hbox.getChildren().addAll(chatName, inputChatName, loginButton, logoutButton);
//			logoutButton.setDisable(true);

			vbox2 = new VBox(6);
			vbox2.setPrefWidth(200);
			vbox2.setPadding(new Insets(0, 10, 0, 10));
			vbox2.getChildren().addAll(chatView, chatText, inputChat, sendButton, logoutButton);

			heading = new Label("Chat");
	        heading.setFont(new Font("Arial", 20));
	        heading.setPadding(new Insets(10, 0, 10, 10));

	        vbox = new VBox(4);
			vbox.getChildren().addAll(vbox2);

			setTop(heading);
			setCenter(vbox);
//			setRight(hbox);
//			setBottom(hbox);

			this.messages = FXCollections.observableArrayList();
			this.chatView.setItems(messages);

			loginView = new ViewChatLogin();
			this.userName = loginView.showAndWait();
	}

	public String getUserName() {
		return this.userName.get();
	}

	public Button getSendButton() {
		return sendButton;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public String getInputChat() {
		return inputChat.getText();
	}

	public ObservableList<String> getMessages() {
		return this.messages;
	}

}
