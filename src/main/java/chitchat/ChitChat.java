package chitchat;

import java.io.IOException;

import chitchat.command.Parser;
import chitchat.storage.Storage;
import chitchat.task.TaskList;
import chitchat.ui.Ui;

/**
 * A task manager program that allows users to manage tasks through a command-line interface.
 * Tasks are saved to a file ('chitchat.txt') on the hard disk and the list is loaded on start up of the chatbot.
 */
public class ChitChat {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes the chatbot.
     * Loads tasks from the storage file, or generates an empty list if there are no saved tasks.
     *
     * @param filePath Path to the file where tasks are saved.
     */
    public ChitChat(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError("Error: Unable to load tasks. Generating empty list.");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
        parser = new Parser(tasks, ui, storage);
    }

    /**
     * Starts the chatbot and processes user commands.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            parser.parseCommand(input);
        }
    }

    /**
     * Initializes and runs the chatbot.
     *
     * @param args
     */
    public static void main(String[] args) {
        new ChitChat("data/chitchat.txt").run();
    }
}

