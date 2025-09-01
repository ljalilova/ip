import java.util.Scanner;

public class Joel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String divider = """
                ____________________________________________________________
                """;
        String greeting = divider + "Hello! I'm Joel.\nWhat can I do for you?\n" + divider;
        String exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;
        Task[] tasks = new Task[100];
        int currentListIndex = 0;

        System.out.println(greeting);
        String userInput = scanner.nextLine().trim();

        while (!userInput.equals("bye")) {
            String[] commandTokens = userInput.split("\\s+");
            if (commandTokens.length == 1 && userInput.equals("list")) {
                if (tasks[0] == null) {
                    System.out.println("Whoops! Your list is empty.");
                    break;
                } else {
                    for (int i = 0; i < currentListIndex; i++) {
                        System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    }
                }
            } else if (commandTokens.length == 2 && (commandTokens[0].equals("mark") || commandTokens[0].equals("unmark"))) {
                try {
                    int index = Integer.parseInt(commandTokens[1]);
                    if (tasks[index - 1] != null) {
                        switch (commandTokens[0]) {
                        case "mark" -> {
                            tasks[index - 1].setDone();
                            System.out.println("Task " + index + " has been marked completed.");
                            System.out.println(" [" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription());
                        }
                        case "unmark" -> {
                            tasks[index - 1].setUndone();
                            System.out.println("Task " + index + " has been marked as not done.");
                            System.out.println(" [" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription());
                        }
                        }
                    } else {
                        System.out.println("There is no such task.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }
            } else {
                tasks[currentListIndex] = new Task(userInput);
                currentListIndex++;
                System.out.println(divider + "added: " + userInput + "\n" + divider);
            }
            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}