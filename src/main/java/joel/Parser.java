package joel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    public static String[] parseCommand(String input) {
        return input.trim().split("\\s+");
    }

    public static String formatToDo(String[] tokens) {
        if (tokens.length == 1) return "";
        return String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    public static String[] formatDeadline(String[] tokens) {
        int byIndex = Arrays.asList(tokens).indexOf("/by");
        if (byIndex <= 1 || byIndex == tokens.length - 1) return new String[0];
        String desc = String.join(" ", Arrays.copyOfRange(tokens, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
        return new String[]{desc, by};
    }

    public static String[] formatEvent(String[] tokens) {
        int fromIndex = Arrays.asList(tokens).indexOf("/from");
        int toIndex = Arrays.asList(tokens).indexOf("/to");
        if (fromIndex <= 1 || toIndex <= fromIndex || toIndex == tokens.length - 1) return new String[0];
        String desc = String.join(" ", Arrays.copyOfRange(tokens, 1, fromIndex));
        String from = String.join(" ", Arrays.copyOfRange(tokens, fromIndex + 1, toIndex));
        String to = String.join(" ", Arrays.copyOfRange(tokens, toIndex + 1, tokens.length));
        return new String[]{desc, from, to};
    }

    public static LocalDateTime parseDateTime(String input) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, inputFormat);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(input);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    public static Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task = switch (type) {
            case "T" -> new ToDo(desc);
            case "D" -> {
                if (parts.length < 4) yield null;
                LocalDateTime dateTime = parseDateTime(parts[3]);
                yield dateTime != null ? new Deadline(desc, dateTime) : null;
            }
            case "E" -> {
                if (parts.length < 4) yield null;
                String[] times = parts[3].split(" to ");
                yield times.length == 2 ? new Event(desc, times[0], times[1]) : null;
            }
            default -> null;
        };

        if (task != null && isDone) task.setDone();
        return task;
    }

    public static String formatTask(Task task) {
        String type;
        if (task instanceof ToDo) type = "T";
        else if (task instanceof Deadline) type = "D";
        else if (task instanceof Event) type = "E";
        else type = "?";

        String status = task.getStatusIcon().equals("X") ? "1" : "0";
        if (task instanceof Deadline d) {
            return String.join(" | ", type, status, d.getDescription(), d.by.toString());
        } else if (task instanceof Event e) {
            return String.join(" | ", type, status, e.getDescription(), e.from + " to " + e.to);
        } else {
            return String.join(" | ", type, status, task.getDescription());
        }
    }
}