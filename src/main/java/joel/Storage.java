package joel;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "data/joel.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                File folder = new File("data");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating joel.txt.");
                return tasks;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    // Skip corrupted lines
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading joel.txt.");
        }

        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to joel.txt.");
        }
    }

    private static String formatTask(Task task) {
        String type;
        if (task instanceof ToDo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        } else {
            type = "?";
        }

        String status = task.getStatusIcon().equals("X") ? "1" : "0";
        if (task instanceof Deadline d) {
            return String.join(" | ", type, status, d.getDescription(), d.by);
        } else if (task instanceof Event e) {
            return String.join(" | ", type, status, e.getDescription(), e.from + " to " + e.to);
        } else {
            return String.join(" | ", type, status, task.getDescription());
        }
    }

    private static Task parseLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task;
        switch (type) {
        case "T" -> task = new ToDo(desc);
        case "D" -> {
            if (parts.length < 4) return null;
            task = new Deadline(desc, parts[3]);
        }
        case "E" -> {
            if (parts.length < 4) return null;
            String[] times = parts[3].split(" to ");
            if (times.length != 2) return null;
            task = new Event(desc, times[0], times[1]);
        }
        default -> task = null;
        }

        if (task != null && isDone) {
            task.setDone();
        }
        return task;
    }
}
