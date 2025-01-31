package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    public String getByDateString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
        String formattedDateTime = by.format(outputFormatter);
        return formattedDateTime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByDateString() + ")";
    }

    @Override
    public String getDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + getByDateString();
    }
}