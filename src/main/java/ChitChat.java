import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ChitChat is a task manager program that allows users to manage tasks through a command-line interface.
 * Tasks are saved to a file ('chitchat.txt') on the hard disk and the list is loaded on start up of the chatbot.
 */
public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Storage storage = new Storage("./data/chitchat.txt");

        ArrayList<Task> tasks;

        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Error: Unable to load tasks. Generating empty list.");
            tasks = new ArrayList<>();
        }

        String line = "------------------------------------------------------------------------------------------";
        System.out.println(line + "\nHey there, I'm ChitChat!\nWhat can I do for you?\n" + line);

        while (true) {
            try {
                String input = scan.nextLine();

                if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println(line + "\nYour task list is empty!\n" + line);
                    } else {
                        System.out.println(line + "\nHere are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println(line);
                    }

                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    tasks.get(taskIndex).setDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
                    System.out.println(line);
                    storage.saveTasks(tasks);

                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    tasks.get(taskIndex).setNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
                    System.out.println(line);
                    storage.saveTasks(tasks);

                } else if (input.startsWith("todo")) {

                    if (input.length() <= 5) {
                        throw new ChitChatException("The description of a task cannot be empty!\n" + line);
                    }

                    String description = input.substring(5);
                    tasks.add(new Todo(description));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  "
                            + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + line);
                    storage.saveTasks(tasks);

                } else if (input.startsWith("deadline")) {

                    if (!input.contains(" /by ")) {
                        throw new ChitChatException(
                                "Invalid format! Please use: deadline <task> /by <yyyy-MM-dd HHmm>.\n" + line);
                    }

                    String[] parts = input.substring(9).split(" /by ");
                    String description = parts[0];
                    String by = parts[1];

                    if (description.isEmpty() || by.isEmpty()) {
                        throw new ChitChatException(
                                "Invalid format! Please use: deadline <task> /by <yyyy-MM-dd HHmm>.\n" + line);
                    }

                    tasks.add(new Deadline(description, by));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  "
                            + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + line);
                    storage.saveTasks(tasks);

                } else if (input.startsWith("event")) {

                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new ChitChatException("Invalid format! "
                                + "Please use: event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>.\n"
                                + line);
                    }

                    String[] parts = input.substring(6).split(" /from | /to ");
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];

                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new ChitChatException("Invalid format! "
                                + "Please use: event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>.\n"
                                + line);
                    }

                    tasks.add(new Event(description, from, to));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  "
                            + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + line);
                    storage.saveTasks(tasks);

                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:\n  " + tasks.get(taskIndex));
                    tasks.remove(taskIndex);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    System.out.println(line);
                    storage.saveTasks(tasks);

                } else if (input.equals("bye")) {
                    System.out.println(line + "\nBye! Hope to see you again soon! :)\n" + line);
                    break;

                } else {
                    throw new ChitChatException("Sorry, you need to use keywords "
                            + "todo/deadline/event to specify your tasks!\n" + line);
                }

            } catch (ChitChatException | IOException e) {
                System.out.println(line + "\nError: " + e.getMessage());
            }
        }
    }
}

