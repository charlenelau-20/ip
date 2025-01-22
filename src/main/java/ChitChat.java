import java.util.Scanner;

public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = "---------------------------------------------";
        System.out.println(line + "\nHey there! I'm ChitChat\nWhat can I do for you?\n" + line);

        String input = scan.nextLine();

        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = scan.nextLine();
        }
        System.out.println(line + "\nBye! Hope to see you again soon! :)\n" + line);
    }
}
