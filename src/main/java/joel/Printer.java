package joel;

public class Printer {

    private static final String divider = "____________________________________________________________";

    public static void printGreeting() {
        System.out.println(divider);
        System.out.println(" Hello! I'm Joel.\n What can I do for you?");
        System.out.println(divider);
    }

    public static void printExitMessage() {
        System.out.println(divider);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

    public static void printTaskAdded(Task task, int count) {
        System.out.println(divider);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(divider);
    }

    public static void printTaskList(Task[] tasks, int count) {
        System.out.println(divider);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println(divider);
    }

    public static void printUnknownCommand() {
        System.out.println(divider);
        System.out.println(" Sorry, I didn't understand that command.");
        System.out.println(" Try one of these:");
        System.out.println("   - todo <description>");
        System.out.println("   - deadline <description> /by <date/time>");
        System.out.println("   - event <description> /from <start> /to <end>");
        System.out.println("   - list");
        System.out.println("   - mark <task number>");
        System.out.println("   - unmark <task number>");
        System.out.println(divider);
    }

    public static void printEmptyList() {
        System.out.println("Whoops! Your list is empty.");
    }

    public static void printMarkStatus(int index, Task task, boolean isDone) {
        System.out.println(divider);
        if (isDone) {
            System.out.println(" Task " + index + " has been marked completed.");
        } else {
            System.out.println(" Task " + index + " has been marked as not done.");
        }
        System.out.println("   " + task.toString());
        System.out.println(divider);
    }

    public static void printNoSuchTask() {
        System.out.println(" There is no such task.");
    }

    public static void printInvalidTaskNumber() {
        System.out.println(" Please enter a valid task number.");
    }

    public static void printMissingTodoDescription() {
        System.out.println(" Please specify a task after 'todo'.");
    }

    public static void printInvalidDeadlineFormat() {
        System.out.println(divider);
        System.out.println(" Invalid deadline format. Use:");
        System.out.println("   deadline <description> /by <date/time>");
        System.out.println(divider);
    }

    public static void printInvalidEventFormat() {
        System.out.println(divider);
        System.out.println(" Invalid event format. Use:");
        System.out.println("   event <description> /from <start> /to <end>");
        System.out.println(divider);
    }

}