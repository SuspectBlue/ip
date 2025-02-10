package pelopsii.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    protected LocalDateTime from;
    /**
     * The end time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     * Completion status is set to false by default.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

     /**
     * Constructs an Event object with the given completion status, description, start time, and end time.
     *
     * @param isDone      The completion status of the event.
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its type,
     * status, description, start time, and end time.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateFormatter.getDateTimeString(from) + " to: " + DateFormatter.getDateTimeString(to) + ")";
    }

     /**
     * Returns a string representation of the event task's data, suitable for storage.
     * It includes the task type ("E"), completion status ("1" for done, "0" for not done),
     * description, start time, and end time, all separated by " | ".
     *
     * @return The data string of the event task.
     */
    @Override
    public String getDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + DateFormatter.getDateTimeString(from) + " | " + DateFormatter.getDateTimeString(to);
    }
}