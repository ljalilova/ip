package joel;

import java.util.List;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    public void showGreeting() {
        System.out.println(DIVIDER);
        System.out.println(" Hello! I'm Joel.\n What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void showExit() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public void showTaskAdded(Task task, int count) {
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println(DIVIDER);
        if (tasks.isEmpty()) {
            System.out.println("Whoops! Your list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    public void showMarkStatus(int index, Task task, boolean isDone) {
        System.out.println(DIVIDER);
        System.out.println(" Task " + index + " has been marked " + (isDone ? "completed." : "as not done."));
        System.out.println("   " + task);
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showUnknownCommand() {
        System.out.println(DIVIDER);
        System.out.println(" Sorry, I didn't understand that command.");
        System.out.println(" Try one of these:");
        System.out.println("   - todo <description>");
        System.out.println("   - deadline <description> /by <date/time>");
        System.out.println("   - event <description> /from <start> /to <end>");
        System.out.println("   - list");
        System.out.println("   - mark <task number>");
        System.out.println("   - unmark <task number>");
        System.out.println(DIVIDER);
    }
}