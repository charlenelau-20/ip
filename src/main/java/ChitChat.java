import java.util.Scanner;

public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = "---------------------------------------------";
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

