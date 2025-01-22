import java.util.Scanner;

public class ChitChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = "---------------------------------------------";
        System.out.println(line + "\nHey there, I'm ChitChat!\nWhat can I do for you?\n" + line);

        String[] inputArr = new String[100];
        int index = 0;

        while (true) {
            String input = scan.nextLine();

            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + inputArr[i]);
                }
                System.out.println(line);
            } else if (input.equals("bye")) {
                System.out.println(line + "\nBye! Hope to see you again soon! :)\n" + line);
                break;
            } else {
                if (index < inputArr.length) {
                    inputArr[index] = input;
                    System.out.println(line + "\nadded: " + input + "\n" + line);
                    index++;
                }
            }
        }
    }
}
