package chitchat.ui;

import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes a Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints error messages.
     *
     * @param message Message describing the error.
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";
        return "Error: " + message;
    }
}
