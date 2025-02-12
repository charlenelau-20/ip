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
     * Reads and returns user commands.
     *
     * @return String representing user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("---------------------------------------------------"
                + "-------------------------------------------------------");
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

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hey there, I'm ChitChat!\nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints exit message.
     */
    public void showExitMessage() {
        showLine();
        System.out.println("Bye! Hope to see you again soon! :)");
        showLine();
    }
}
