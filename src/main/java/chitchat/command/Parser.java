package chitchat.command;

import java.io.IOException;

import chitchat.exception.ChitChatException;
import chitchat.storage.Storage;
import chitchat.task.Deadline;
import chitchat.task.Event;
import chitchat.task.TaskList;
import chitchat.task.Todo;
import chitchat.ui.Ui;

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
    public void parseCommand(String input) {
        try {
            // Handle list command
            if (input.equals("list")) {
                taskList.listTasks(ui);

            // Handle mark command
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                taskList.markTask(taskIndex);

                ui.showLine();
                System.out.println("Nice! I've marked this task as done:\n  " + taskList.getTasks().get(taskIndex));
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle unmark command
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                taskList.unmarkTask(taskIndex);

                ui.showLine();
                System.out.println("OK, I've marked this task as not done yet:\n  "
                        + taskList.getTasks().get(taskIndex));
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle todo command
            } else if (input.startsWith("todo")) {

                if (input.length() <= 5) {
                    throw new ChitChatException("The description of a task cannot be empty!");
                }

                String description = input.substring(5).trim();

                if (description.isEmpty()) {
                    throw new ChitChatException("Invalid format! Please use: todo <task>.");
                }

                taskList.addTask(new Todo(description));
                ui.showLine();
                System.out.println("Got it. I've added this task:\n" + "  "
                        + taskList.getTasks().get(taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle deadline command
            } else if (input.startsWith("deadline")) {

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
                ui.showLine();
                System.out.println("Got it. I've added this task:\n" + "  "
                        + taskList.getTasks().get(taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle event command
            } else if (input.startsWith("event")) {

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
                ui.showLine();
                System.out.println("Got it. I've added this task:\n" + "  "
                        + taskList.getTasks().get(taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle delete command
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                if (taskIndex < 0 || taskIndex >= taskList.size()) {
                    throw new ChitChatException("Please enter a valid task number!");
                }

                ui.showLine();
                System.out.println("Noted. I've removed this task:\n  " + taskList.getTasks().get(taskIndex));
                taskList.deleteTask(taskIndex);
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
                ui.showLine();
                storage.saveTasks(taskList);

            // Handle find command
            } else if (input.startsWith("find")) {

                if (input.length() <= 5) {
                    throw new ChitChatException("Invalid format! Please use: find <keyword(s)>");
                }

                String keyword = input.substring(5).trim();

                if (keyword.isEmpty()) {
                    throw new ChitChatException("Invalid format! Please use: find <keyword(s)>");
                }

                taskList.findTasks(keyword, ui);
                
            // Handle exit command
            } else if (input.equals("bye")) {
                ui.showLine();
                System.out.println("Bye! Hope to see you again soon! :)");
                ui.showLine();
                System.exit(0);

            // Handle invalid commands
            } else {
                throw new ChitChatException("Sorry, you need to use keywords "
                        + "todo/deadline/event to specify your tasks!");
            }

        } catch (ChitChatException | IOException e) {
            ui.showLine();
            ui.showError(e.getMessage());
            ui.showLine();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            ui.showLine();
            ui.showError("Please enter a valid task number!");
            ui.showLine();
        }
    }
}
