package pelopsii.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.Event;

public class EventCommand extends Command{
    private String description;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    public EventCommand(String input) throws InvalidCommandException {
        System.out.println(input);
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new InvalidCommandException("Event tasks must include a description, event start time and event end time. For example: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] data = input.substring(6).split(" /from ");
        if (data.length != 2) {
            throw new InvalidCommandException("Missing description or date input");
        }
        this.description = data[0];

        String[] dates = data[1].split(" /to ");
        if(dates.length != 2) {
            throw new InvalidCommandException("Missing from or to date");
        }

        try {
            this.fromTime = LocalDateTime.parse(dates[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.toTime = LocalDateTime.parse(dates[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format. Please use the format yyyy-MM-dd HHmm.");
        }

        if(toTime.isBefore(fromTime)) {
            throw new InvalidCommandException("From time must be before To time");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        Event event = new Event(description, fromTime, toTime);
        this.taskList.addTask(event);
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE).append("\n")
                                                            .append(event).append("\n")
                                                            .append("Now you have " + this.taskList.getSize() + (this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list."));
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
