package chitchat;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object with given text and image.
     *
     * @param text Text to be shown in the dialogue box.
     * @param img Image representing speaker (user/chatbot).
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        Circle clip = new Circle(37);
        clip.setCenterX(50);
        clip.setCenterY(50);
        displayPicture.setClip(clip);

        if (isUser) {
            dialog.getStyleClass().add("user-label");
        } else {
            dialog.getStyleClass().add("bot-label");
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates and returns a DialogBox showing user input and image.
     *
     * @param text Text input by the user.
     * @param img User image.
     * @return DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates and returns a DialogBox showing ChitChat's response and image.
     *
     * @param text Text response by ChitChat.
     * @param img ChitChat image.
     * @return DialogBox.
     */
    public static DialogBox getChitChatDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img, false);
        if (isError) {
            db.dialog.setStyle("-fx-background-color: tomato; -fx-border-color: red; -fx-text-fill: white");
        }
        db.flip();
        return db;
    }
}

