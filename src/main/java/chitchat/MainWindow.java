package chitchat;

import chitchat.command.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChitChat chitchat;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image chitchatImage = new Image(this.getClass().getResourceAsStream("/images/chitchatImage.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String welcomeMessage = "Hey there, I'm ChitChat!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getChitChatDialog(welcomeMessage, chitchatImage));
    }

    /**
     * Injects the ChitChat instance.
     */
    public void setChitChat(ChitChat c) {
        this.chitchat = c;

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChitChat's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = chitchat.processCommand(input);
        System.out.println("response: " + response);
        dialogContainer.getChildren().add(DialogBox.getChitChatDialog(response, chitchatImage));
        userInput.clear();
    }
}

