package chitchat;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the ChitChat application.
     *
     * @param args Command-line arguments passed into the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
