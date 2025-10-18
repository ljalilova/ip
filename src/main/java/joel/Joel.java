package joel;

import java.util.ArrayList;
import java.util.Scanner;

public class Joel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = Storage.loadTasks();
        int currentListIndex = tasks.size();

        Printer.printGreeting();
        String userInput = scanner.nextLine().trim();

        while (!userInput.equals("bye")) {
            String[] commandTokens = userInput.split("\\s+");

            if (commandTokens.length == 1 && userInput.equals("list")) {
                if (tasks.isEmpty()) {
                    Printer.printEmptyList();
                } else {
                    Printer.printTaskList(tasks);
                }

            } else if (commandTokens.length == 2 &&
                    (commandTokens[0].equals("mark") || commandTokens[0].equals("unmark"))) {
                try {
                    int index = Integer.parseInt(commandTokens[1]);
                    if (index >= 1 && index <= tasks.size()) {
                        Task task = tasks.get(index - 1);
                        if (commandTokens[0].equals("mark")) {
                            task.setDone();
                            Printer.printMarkStatus(index, task, true);
                        } else {
                            task.setUndone();
                            Printer.printMarkStatus(index, task, false);
                        }
                        Storage.saveTasks(tasks);
                    } else {
                        Printer.printNoSuchTask();
                    }
                } catch (NumberFormatException e) {
                    Printer.printInvalidTaskNumber();
                }

            } else if (commandTokens[0].equals("delete") && commandTokens.length == 2) {
                try {
                    int index = Integer.parseInt(commandTokens[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removed = tasks.remove(index);
                        Printer.printTaskDeleted(removed, tasks.size());
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
                        Task newTask = new ToDo(taskDesc);
                        tasks.add(newTask);
                        Printer.printTaskAdded(newTask, tasks.size());
                        Storage.saveTasks(tasks);
                        currentListIndex++;
                    }
                }

                case "deadline" -> {
                    String[] formatted = TaskInputFormatter.formatDeadline(commandTokens);
                    if (formatted.length == 0) {
                        Printer.printInvalidDeadlineFormat();
                    } else {
                        Task newTask = new Deadline(formatted[0], formatted[1]);
                        tasks.add(newTask);
                        Printer.printTaskAdded(newTask, tasks.size());
                        Storage.saveTasks(tasks);
                        currentListIndex++;
                    }
                }

                case "event" -> {
                    String[] formatted = TaskInputFormatter.formatEvent(commandTokens);
                    if (formatted.length == 0) {
                        Printer.printInvalidEventFormat();
                    } else {
                        Task newTask = new Event(formatted[0], formatted[1], formatted[2]);
                        tasks.add(newTask);
                        Printer.printTaskAdded(newTask, tasks.size());
                        Storage.saveTasks(tasks);
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
