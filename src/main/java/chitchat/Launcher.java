package chitchat;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point for launching the ChitChat application.
     *
     * @param args Command-line arguments passed into the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
