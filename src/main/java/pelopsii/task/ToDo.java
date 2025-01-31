package pelopsii.task;

/**
 * Represents a simple to-do task with a description.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     * Completion status is set to false by default.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo object with the given completion status and description.
     *
     * @param isDone      The completion status of the to-do task.
     * @param description The description of the to-do task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the to-do task, including its type and status/description from the superclass.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task's data, suitable for storage.
     * It includes the task type ("T"), completion status ("1" for done, "0" for not done),
     * and the description, all separated by " | ".
     *
     * @return The data string of the to-do task.
     */
    @Override
    public String getDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}