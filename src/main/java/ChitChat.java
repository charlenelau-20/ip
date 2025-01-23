import java.util.ArrayList;
import java.util.Scanner;

public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = "------------------------------------------------------------------------------------------";
        System.out.println(line + "\nHey there, I'm ChitChat!\nWhat can I do for you?\n" + line);

        ArrayList<Task> task = new ArrayList<>();

        while (true) {
            try {
                String input = scan.nextLine();

                if (input.equals("list")) {
                    if (task.isEmpty()) {
                        System.out.println(line + "\nYour task list is empty!\n" + line);
                    } else {
                        System.out.println(line + "\nHere are the tasks in your list:");
                        for (int i = 0; i < task.size(); i++) {
                            System.out.println((i + 1) + "." + task.get(i));
                        }
                        System.out.println(line);
                    }

                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= task.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    task.get(taskIndex).setDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:\n  " + task.get(taskIndex));
                    System.out.println(line);

                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= task.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    task.get(taskIndex).setNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:\n  " + task.get(taskIndex));
                    System.out.println(line);

                } else if (input.startsWith("todo")) {

                    if (input.length() <= 5) {
                        throw new ChitChatException("The description of a task cannot be empty!");
                    }

                    String description = input.substring(5);
                    task.add(new Todo(description));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " task(s) in the list.\n" + line);

                } else if (input.startsWith("deadline")) {

                    if (!input.contains(" /by ")) {
                        throw new ChitChatException("Invalid format! Please use: deadline <task> /by <when>.\n" + line);
                    }

                    String[] part = input.substring(9).split(" /by ");
                    String description = part[0];
                    String by = part[1];

                    if (description.isEmpty() || by.isEmpty()) {
                        throw new ChitChatException("Invalid format! Please use: deadline <task> /by <when>.\n" + line);
                    }

                    task.add(new Deadline(description, by));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " task(s) in the list.\n" + line);

                } else if (input.startsWith("event")) {

                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new ChitChatException("Invalid format! " +
                                "Please use: event <event name> /from <when> /to <when>.\n" + line);
                    }

                    String[] part = input.substring(6).split(" /from | /to ");
                    String description = part[0];
                    String from = part[1];
                    String to = part[2];

                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new ChitChatException("Invalid format! " +
                                "Please use: event <event name> /from <when> /to <when>.\n" + line);
                    }

                    task.add(new Event(description, from, to));
                    System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task.get(task.size() - 1));
                    System.out.println("Now you have " + task.size() + " task(s) in the list.\n" + line);

                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

                    if (taskIndex < 0 || taskIndex >= task.size()) {
                        throw new ChitChatException("Please enter a valid task number!\n" + line);
                    }

                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:\n  " + task.get(taskIndex));
                    task.remove(taskIndex);
                    System.out.println("Now you have " + task.size() + " task(s) in the list.");
                    System.out.println(line);

                } else if (input.equals("bye")) {
                    System.out.println(line + "\nBye! Hope to see you again soon! :)\n" + line);
                    break;

                } else {
                    throw new ChitChatException("Sorry, you need to use keywords " +
                            "todo/deadline/event to specify your tasks!\n" + line);
                }

            } catch (ChitChatException e) {
                System.out.println(line + "\nError: " + e.getMessage());
            }
        }
    }
}

