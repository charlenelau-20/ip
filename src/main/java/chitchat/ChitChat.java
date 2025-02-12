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
@SuppressWarnings("checkstyle:CommentsIndentation")
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
     * Constructs a ChitChat object with a default filepath to store tasks.
     */
    public ChitChat() {
        this("./data/chitchat.txt");
    }

    /**
     * Processes user input/command and returns the response.
     *
     * @param input User input/command
     * @return Response to the command
     */
    public String processCommand(String input) {
        return parser.parseCommand(input);
    }
}

