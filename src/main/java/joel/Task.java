package joel;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as not done.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted task string.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}