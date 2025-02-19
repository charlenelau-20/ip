package chitchat.command;

import java.io.IOException;

import chitchat.exception.ChitChatException;
import chitchat.storage.Storage;
import chitchat.task.Deadline;
import chitchat.task.Event;
import chitchat.task.TaskList;
import chitchat.task.Todo;
import chitchat.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Handles user commands by parsing input and executing corresponding actions.
 */
public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a Parser object which processes user commands.
     *
     * @param taskList Task list.
     * @param ui User interface to display messages to the user.
     * @param storage Storage to store saved tasks.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses user input and executes corresponding action.
     *
     * @param input User input.
     */
    public String parseCommand(String input) {
        String[] userInput = input.split(" ", 2);
        String command = userInput[0].toLowerCase();
        try {
            switch (command) {
            case "list" -> {
                return handleList();
            }
            case "mark" -> {
                return handleMark(input);
            }
            case "unmark" -> {
                return handleUnmark(input);
            }
            case "todo" -> {
                return handleTodo(input);
            }
            case "deadline" -> {
                return handleDeadline(input);
            }
            case "event" -> {
                return handleEvent(input);
            }
            case "delete" -> {
                return handleDelete(input);
            }
            case "find" -> {
                return handleFind(input);
            }
            case "help" -> {
                return handleHelp();
            }
            case "bye" -> {
                return handleExit();
            }
            default -> {
                return "Please use command 'help' to view command list.";
            }
            }
        } catch (ChitChatException | IOException e) {
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "Please enter a valid task number!";
        }
    }

    /**
     * Handles list command.
     *
     * @return List of tasks.
     */
    private String handleList() {
        return taskList.listTasks(ui);
    }

    /**
     * Handles mark command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If index is out of bounds.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleMark(String input) throws ChitChatException, IOException {
        String output = "";
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        taskList.markTask(taskIndex);
        output = "Nice! I've marked this task as done:\n  " + taskList.getTasks().get(taskIndex);
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles unmark command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If index is out of bounds.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleUnmark(String input) throws ChitChatException, IOException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        taskList.unmarkTask(taskIndex);
        String output = "OK, I've marked this task as not done yet:\n" + taskList.getTasks().get(taskIndex);
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles todo command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If input format is incorrect.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleTodo(String input) throws ChitChatException, IOException {
        if (input.length() <= 5) {
            throw new ChitChatException("Invalid format! Please use: todo <task>.");
        }

        String description = input.substring(5).trim();

        if (description.isEmpty()) {
            throw new ChitChatException("Invalid format! Please use: todo <task>.");
        }

        taskList.addTask(new Todo(description));
        String output = "Got it. I've added this task:\n" + "  "
                + taskList.getTasks().get(taskList.size() - 1) + "\nNow you have " + taskList.size()
                + " task(s) in the list.";
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles deadline command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If input format is incorrect.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleDeadline(String input) throws ChitChatException, IOException {
        if (!input.contains(" /by ")) {
            throw new ChitChatException(
                    "Invalid format! Please use: deadline <task> /by <yyyy-MM-dd HHmm>.");
        }

        String[] parts = input.substring(9).split(" /by ");
        String description = parts[0].trim();
        String by = parts[1];

        if (description.isEmpty() || by.isEmpty()) {
            throw new ChitChatException(
                    "Invalid format! Please use: deadline <task> /by <yyyy-MM-dd HHmm>.");
        }

        taskList.addTask(new Deadline(description, by));
        String output = "Got it. I've added this task:\n" + "  "
                + taskList.getTasks().get(taskList.size() - 1) + "\nNow you have " + taskList.size()
                + " task(s) in the list.";
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles event command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If input format is incorrect.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleEvent(String input) throws ChitChatException, IOException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ChitChatException("Invalid format! "
                    + "Please use: event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>.");
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        String description = parts[0].trim();
        String from = parts[1];
        String to = parts[2];

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ChitChatException("Invalid format! "
                    + "Please use: event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>.");
        }

        taskList.addTask(new Event(description, from, to));
        String output = "Got it. I've added this task:\n" + "  "
                + taskList.getTasks().get(taskList.size() - 1) + "\nNow you have " + taskList.size()
                + " task(s) in the list.";
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles delete command.
     *
     * @param input User input.
     * @return String of response.
     * @throws ChitChatException If index is out of bounds.
     * @throws IOException If there is a problem saving the tasks.
     */
    private String handleDelete(String input) throws ChitChatException, IOException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new ChitChatException("Please enter a valid task number!");
        }
        String output = "Noted. I've removed this task:\n  " + taskList.getTasks().get(taskIndex);
        taskList.deleteTask(taskIndex);
        output += "\nNow you have " + taskList.size() + " task(s) in the list.";
        storage.saveTasks(taskList);
        return output;
    }

    /**
     * Handles find command.
     *
     * @param input User input.
     * @return String of task(s) found.
     * @throws ChitChatException If input format is incorrect.
     */
    private String handleFind(String input) throws ChitChatException {
        if (input.length() <= 5) {
            throw new ChitChatException("Invalid format! Please use: find <keyword(s)>");
        }

        String keyword = input.substring(5).trim();

        if (keyword.isEmpty()) {
            throw new ChitChatException("Invalid format! Please use: find <keyword(s)>");
        }

        return taskList.findTasks(keyword, ui);
    }

    /**
     * Handles help command.
     *
     * @return List of commands that can be used.
     */
    private String handleHelp() {
        return "Here is a list of the commands you can use:\n1. todo <task>\n-> adds a todo task\n\n2. deadline"
                + " <task> /by <yyyy-mm-dd HHmm>\n-> adds a deadline task\n\n3. event <event name> /from "
                + "<yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>\n-> adds an event\n\n4. list\n-> lists all tasks in "
                + "your task list\n\n5. mark / unmark <task number>\n-> marks task as done or not done\n\n"
                + "6. delete <task number>\n-> deletes task\n\n7. find <keyword(s)>\n-> finds tasks which contain"
                + " <keyword(s)>\n\n8. bye\n-> exits application";
    }

    /**
     * Handles bye (exit) command.
     *
     * @return String of response.
     */
    private String handleExit() {
        String output = "Bye! Hope to see you again soon! :)";
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
        return output;
    }
}
