package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ViewChat extends BorderPane {


	private Label heading;
	private Button sendButton = new Button("Nachricht senden");
	private Label chatText = new Label("Nachricht:");
	private Label chatName = new Label("Nickname:");
	private ListView chatView = new ListView();
	private TextField inputChat = new TextField();
	private TextField inputChatName = new TextField();
	private Button loginButton = new Button("Login");
	private Button logoutButton = new Button("Logout");
	private VBox vbox2;


	public ViewChat() {

			HBox hbox = new HBox(chatName, inputChatName, loginButton, logoutButton);
			logoutButton.setDisable(true);

			vbox2 = new VBox(6);
			vbox2.setPrefWidth(200);
			vbox2.setPadding(new Insets(0, 10, 0, 10));
			vbox2.getChildren().addAll(chatView, chatText, inputChat, sendButton);

			heading = new Label("Chat");
	        heading.setFont(new Font("Arial", 20));
	        heading.setPadding(new Insets(10, 0, 10, 10));

			setTop(heading);
			setCenter(vbox2);
//			setRight(hbox);
			setBottom(hbox);



	}

//folgende Listenerverknüpfung der Zeit halber von anderer Lösung inspiriert - bitte anpassen! Errorhandling war aber glaub ich nicht unbedingt ein Muss!
//	public void addLoginEvent(controller.LoginListener listener){
//		loginButton.setOnAction(e->{
//			if(!inputChatName.getText().trim().isEmpty()){
//				listener.login(inputChatName.getText());
//				loginButton.setDisable(true);
//				logoutButton.setDisable(false);
//			}else{
//				ExceptionBox.display("Fehler!","Bitte geben Sie einen Chat-Namen ein!");
//			}
//		});
//
//
//	}
//
//	public void addLogoutEvent(controller.LogoutListener listener){
//		logoutButton.setOnAction(e->{
//			listener.logout(inputChatName.getText());
//			loginButton.setDisable(false);
//			logoutButton.setDisable(true);
//		});
//		inputChatName.setEditable(true);
//	}
//
//	public void displayMessage(String message) {
//		chatView.appendText(message + "\n");
//	}

	public Button getSendButton() {
		return sendButton;
	}

	public void setSendButton(Button sendButton) {
		this.sendButton = sendButton;
	}

	public ListView getChatView() {
		return chatView;
	}

	public void setChatView(ListView chatView) {
		this.chatView = chatView;
	}

	public TextField getInputChat() {
		return inputChat;
	}

	public void setInputChat(TextField inputChat) {
		this.inputChat = inputChat;
	}

	public TextField getInputChatName() {
		return inputChatName;
	}

	public void setInputChatName(TextField inputChatName) {
		this.inputChatName = inputChatName;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(Button logoutButton) {
		this.logoutButton = logoutButton;
	}
}
