package pelopsii.command;

import pelopsii.exception.PelopsIIException;
import pelopsii.task.Event;

public class EventCommand extends Command{
    private String description;
    private String fromTime;
    private String toTime;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    public EventCommand(String input) throws PelopsIIException {
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new PelopsIIException("Event tasks must include a description, event start time and event end time. For example: event <description> /from <start_time> /to <end_time>");
        }
        String data = input.split("event ")[1];
        this.description = data.split(" /from ")[0];
        this.fromTime = data.split(" /from ")[1].split(" /to ")[0];
        this.toTime = data.split(" /to ")[1];
    }

    @Override
    public void execute() throws PelopsIIException {
        Event event = new Event(description, fromTime, toTime);
        this.taskList.addTask(event);
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE).append("\n")
                                                            .append(event).append("\n")
                                                            .append("Now you have " + this.taskList.getSize() + (this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list."));
        this.ui.showMessageToUser(sb.toString());
    }
}
