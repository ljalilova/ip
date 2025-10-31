package joel;

import java.util.Scanner;

public class Joel {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Joel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showGreeting();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] tokens = Parser.parseCommand(input);

            if (tokens.length == 1 && tokens[0].equals("list")) {
                ui.showTaskList(tasks.getAll());

            } else if (tokens.length == 2 && (tokens[0].equals("mark") || tokens[0].equals("unmark"))) {
                try {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(index);
                    if (tokens[0].equals("mark")) {
                        task.setDone();
                        ui.showMarkStatus(index + 1, task, true);
                    } else {
                        task.setUndone();
                        ui.showMarkStatus(index + 1, task, false);
                    }
                    storage.saveTasks(tasks.getAll());
                } catch (Exception e) {
                    ui.showError("Invalid task number.");
                }

            } else {
                switch (tokens[0]) {
                case "todo" -> {
                    String desc = Parser.formatToDo(tokens);
                    if (desc.isEmpty()) {
                        ui.showError("Please specify a task after 'todo'.");
                    } else {
                        Task task = new ToDo(desc);
                        tasks.add(task);
                        ui.showTaskAdded(task, tasks.size());
                        storage.saveTasks(tasks.getAll());
                    }
                }
                case "deadline" -> {
                    String[] parts = Parser.formatDeadline(tokens);
                    if (parts.length == 0) {
                        ui.showError("Invalid deadline format. Use yyyy-MM-dd HH:mm");
                    } else {
                        var dateTime = Parser.parseDateTime(parts[1]);
                        if (dateTime == null) {
                            ui.showError("Could not parse date/time. Use format: yyyy-MM-dd HH:mm");
                        } else {
                            Task task = new Deadline(parts[0], dateTime);
                            tasks.add(task);
                            ui.showTaskAdded(task, tasks.size());
                            storage.saveTasks(tasks.getAll());
                        }
                    }
                }
                case "event" -> {
                    String[] parts = Parser.formatEvent(tokens);
                    if (parts.length == 0) {
                        ui.showError("Invalid event format.");
                    } else {
                        Task task = new Event(parts[0], parts[1], parts[2]);
                        tasks.add(task);
                        ui.showTaskAdded(task, tasks.size());
                        storage.saveTasks(tasks.getAll());
                    }
                }
                default -> ui.showUnknownCommand();
                }
            }

            input = scanner.nextLine();
        }

        ui.showExit();
    }

    public static void main(String[] args) {
        new Joel("data/joel.txt").run();
    }
}