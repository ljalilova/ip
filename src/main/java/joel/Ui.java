package joel;

import java.util.List;

/**
 * Handles all user interaction and output formatting.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Displays the greeting message.
     */
    public void showGreeting() {
        System.out.println(DIVIDER);
        System.out.println(" Hello! I'm Joel.\n What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Displays the exit message.
     */
    public void showExit() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task  The task added.
     * @param count The total number of tasks.
     */
    public void showTaskAdded(Task task, int count) {
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Displays the full list of tasks.
     *
     * @param tasks The list of tasks.
     */
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

    /**
     * Displays the status of a marked or unmarked task.
     *
     * @param index  The task number.
     * @param task   The task.
     * @param isDone True if marked as done, false if unmarked.
     */
    public void showMarkStatus(int index, Task task, boolean isDone) {
        System.out.println(DIVIDER);
        System.out.println(" Task " + index + " has been marked " + (isDone ? "completed." : "as not done."));
        System.out.println("   " + task);
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a message for unknown commands.
     */
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
        System.out.println("   - find <keyword(s)>");
        System.out.println("   - delete <task number>");
        System.out.println(DIVIDER);
    }

    /**
     * Displays matching tasks from a search.
     *
     * @param matches The list of matching tasks.
     */
    public void showMatchingTasks(List<Task> matches) {
        System.out.println(DIVIDER);
        if (matches.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matches.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    public void showTaskDeleted(Task task, int remainingCount) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + remainingCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}