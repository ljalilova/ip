import java.util.Scanner;

public class Joel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String divider = """
                ____________________________________________________________
                """;
        String greeting = divider + "Hello! I'm Joel.\nWhat can I do for you?\n" + divider;
        String exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;
        String[] tasks = new String[100];
        int currentListIndex = 0;

        System.out.println(greeting);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                if (tasks[0] == null) {
                    System.out.println("Whoops! Your list is empty.");
                } else {
                    for (int i = 0; i < currentListIndex; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                tasks[currentListIndex] = userInput;
                currentListIndex++;
                System.out.println(divider + "added: " + userInput + "\n" + divider);
            }
            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}