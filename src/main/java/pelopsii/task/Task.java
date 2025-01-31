package pelopsii.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The completion status of the task. True if done, false otherwise.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description and sets completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with the given completion status and description.
     *
     * @param isDone      The completion status of the task.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns a string representation of the task's completion status.
     * "X" if the task is done, " " otherwise.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task's data, suitable for storage.
     * It consists of the completion status ("1" for done, "0" for not done)
     * followed by " | " and then the description.
     *
     * @return The data string of the task.
     */
    public String getDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}