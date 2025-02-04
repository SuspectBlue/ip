package pelopsii.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pelopsii.exception.PelopsIIException;
import pelopsii.task.Deadline;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime dateTime;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    public DeadlineCommand(String input) throws PelopsIIException {
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new PelopsIIException("Deadline tasks must include both a description and a specified deadline time. For example: deadline <description> /by yyyy-MM-dd HHmm");
        }
        String data = input.split("deadline ")[1];
        this.description = data.split(" /by ")[0];
        String byDate = data.split(" /by ")[1];
        this.dateTime = LocalDateTime.parse(byDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public void execute() throws PelopsIIException {
        Deadline deadline = new Deadline(description, dateTime);
        this.taskList.addTask(deadline);
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE).append("\n")
                                                            .append(deadline).append("\n")
                                                            .append("Now you have " + this.taskList.getSize() + (this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list."));
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}