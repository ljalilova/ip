package joel;

import java.util.Arrays;

public class TaskInputFormatter {
    public static String formatToDo(String[] toDoTask) {
        if (toDoTask.length == 1) {
            return "";
        }
        return String.join(" ", Arrays.copyOfRange(toDoTask, 1, toDoTask.length));

    }

    public static String[] formatDeadline(String[] tokens) {
        int byIndex = -1;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        if (byIndex == -1 || byIndex == 1 || byIndex == tokens.length - 1) {
            return new String[0]; // Invalid format
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, byIndex));
        String byTime = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
        return new String[]{description, byTime};
    }

    public static String[] formatEvent(String[] tokens) {
        int fromIndex = -1;
        int toIndex = -1;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/from")) {
                fromIndex = i;
            } else if (tokens[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1 || toIndex == -1 || fromIndex <= 1 || toIndex <= fromIndex + 1 || toIndex == tokens.length - 1) {
            return new String[0]; // Invalid format
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, fromIndex));
        String fromTime = String.join(" ", Arrays.copyOfRange(tokens, fromIndex + 1, toIndex));
        String toTime = String.join(" ", Arrays.copyOfRange(tokens, toIndex + 1, tokens.length));
        return new String[]{description, fromTime, toTime};
    }

}
