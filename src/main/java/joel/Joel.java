package joel;

import java.util.Scanner;

public class Joel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int currentListIndex = 0;

        Printer.printGreeting();
        String userInput = scanner.nextLine().trim();

        while (!userInput.equals("bye")) {
            String[] commandTokens = userInput.split("\\s+");

            if (commandTokens.length == 1 && userInput.equals("list")) {
                if (tasks[0] == null) {
                    Printer.printEmptyList();
                } else {
                    Printer.printTaskList(tasks, currentListIndex);
                }

            } else if (commandTokens.length == 2 &&
                    (commandTokens[0].equals("mark") || commandTokens[0].equals("unmark"))) {
                try {
                    int index = Integer.parseInt(commandTokens[1]);
                    if (tasks[index - 1] != null) {
                        if (commandTokens[0].equals("mark")) {
                            tasks[index - 1].setDone();
                            Printer.printMarkStatus(index, tasks[index - 1], true);
                        } else {
                            tasks[index - 1].setUndone();
                            Printer.printMarkStatus(index, tasks[index - 1], false);
                        }
                    } else {
                        Printer.printNoSuchTask();
                    }
                } catch (NumberFormatException e) {
                    Printer.printInvalidTaskNumber();
                }

            } else {
                switch (commandTokens[0]) {
                case "todo" -> {
                    String taskDesc = TaskInputFormatter.formatToDo(commandTokens);
                    if (taskDesc.isEmpty()) {
                        Printer.printMissingTodoDescription();
                    } else {
                        tasks[currentListIndex] = new ToDo(taskDesc);
                        Printer.printTaskAdded(tasks[currentListIndex], currentListIndex + 1);
                        currentListIndex++;
                    }
                }

                case "deadline" -> {
                    String[] formatted = TaskInputFormatter.formatDeadline(commandTokens);
                    if (formatted.length == 0) {
                        Printer.printInvalidDeadlineFormat();
                    } else {
                        tasks[currentListIndex] = new Deadline(formatted[0], formatted[1]);
                        Printer.printTaskAdded(tasks[currentListIndex], currentListIndex + 1);
                        currentListIndex++;
                    }
                }

                case "event" -> {
                    String[] formatted = TaskInputFormatter.formatEvent(commandTokens);
                    if (formatted.length == 0) {
                        Printer.printInvalidEventFormat();
                    } else {
                        tasks[currentListIndex] = new Event(formatted[0], formatted[1], formatted[2]);
                        Printer.printTaskAdded(tasks[currentListIndex], currentListIndex + 1);
                        currentListIndex++;
                    }
                }

                default -> Printer.printUnknownCommand();
                }
            }

            userInput = scanner.nextLine().trim();
        }

        Printer.printExitMessage();
    }
}