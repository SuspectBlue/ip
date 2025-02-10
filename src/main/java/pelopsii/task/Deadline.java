package pelopsii.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task with a description and a deadline date and time.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline date and time.
     * Completion status is set to false by default.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the given completion status, description, and deadline date and time.
     *
     * @param isDone      The completion status of the deadline task.
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its type,
     * status, description, and the formatted deadline date and time.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.getDateTimeString(by) + ")";
    }

     /**
     * Returns a string representation of the deadline task's data, suitable for storage.
     * It includes the task type ("D"), completion status ("1" for done, "0" for not done),
     * description, and the formatted deadline date and time, all separated by " | ".
     *
     * @return The data string of the deadline task.
     */
    @Override
    public String getDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + DateFormatter.getDateTimeString(by);
    }
}