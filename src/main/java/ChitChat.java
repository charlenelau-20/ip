import java.util.Scanner;

public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = "--------------------------------------------------------";
        System.out.println(line + "\nHey there, I'm ChitChat!\nWhat can I do for you?\n" + line);

        Task[] task = new Task[100];
        int index = 0;

        while (true) {
            String input = scan.nextLine();

            if (input.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + task[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                task[taskIndex].setDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:\n  " + task[taskIndex]);
                System.out.println(line);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                task[taskIndex].setNotDone();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:\n  " + task[taskIndex]);
                System.out.println(line);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                task[index] = new Todo(description);
                index++;
                System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task[index - 1]);
                System.out.println("Now you have " + index + " task(s) in the list\n" + line);
            } else if (input.startsWith("deadline")) {
                String[] part = input.substring(9).split(" /by ");
                String description = part[0];
                String by = part[1];
                task[index] = new Deadline(description, by);
                index++;
                System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task[index - 1]);
                System.out.println("Now you have " + index + " task(s) in the list\n" + line);
            } else if (input.startsWith("event")) {
                String[] part = input.substring(6).split(" /from | /to ");
                String description = part[0];
                String from = part[1];
                String to = part[2];
                task[index] = new Event(description, from, to);
                index++;
                System.out.println(line + "\nGot it. I've added this task:\n" + "  " + task[index - 1]);
                System.out.println("Now you have " + index + " task(s) in the list\n" + line);
            } else if (input.equals("bye")) {
                System.out.println(line + "\nBye! Hope to see you again soon! :)\n" + line);
                break;
            } else {
                if (index < task.length) {
                    task[index] = new Task(input);
                    System.out.println(line + "\nadded: " + input + "\n" + line);
                    index++;
                }
            }
        }
    }
}

