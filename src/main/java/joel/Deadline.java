package joel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * The deadline is stored as a {@link LocalDateTime} and formatted for display.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline as a {@code LocalDateTime}.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including the formatted deadline date and time.
     *
     * @return A formatted string showing task type, status, description, and deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}