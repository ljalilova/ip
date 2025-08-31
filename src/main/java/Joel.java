import java.util.Scanner;

public class Joel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String divider = """
                ____________________________________________________________
                """;
        String greeting = divider + "Hello! I'm Joel.\nWhat can I do for you?\n" + divider;
        String exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;

        System.out.println(greeting);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println(divider + userInput + "\n" + divider);
            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}
