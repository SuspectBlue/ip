package pelopsii.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.Deadline;

/**
 * Represents a command to create a deadline task, which includes a description and a deadline time.
 * The user input is parsed to extract the description and the deadline time, ensuring the format is correct.
 * The deadline time must be specified in the format "yyyy-MM-dd HHmm".
 * If the input is malformed or the date format is incorrect, an InvalidCommandException is thrown.
 * 
 * This command is responsible for storing the task description and its deadline, and handling errors related 
 * to input validation and date parsing.
 * 
 * Example usage:
 * <pre>
 * DeadlineCommand deadlineCommand = new DeadlineCommand("deadline Submit report /by 2025-02-20 1800");
 * </pre>
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime dateTime;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    /**
     * Constructs a DeadlineCommand by parsing the user input.
     *
     * @param input The user input containing the description and deadline.
     * @throws InvalidCommandException If the input format is incorrect.
     */
    public DeadlineCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new InvalidCommandException("Deadline tasks must include both a description and a specified deadline time. For example: deadline <description> /by yyyy-MM-dd HHmm");
        }

        String[] data = input.substring(9).split(" /by ");

        if(data.length != 2) {
            throw new InvalidCommandException("Missing description or date input");
        }

        this.description = data[0];
        String byDate = data[1];

        try {
            this.dateTime = LocalDateTime.parse(byDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format. Please use the format yyyy-MM-dd HHmm.");
        }
        
    }

    @Override
    public void execute() throws PelopsIIException {
        Deadline deadline = new Deadline(description, dateTime);
        this.taskList.addTask(deadline);
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE)
            .append("\n")
            .append(deadline)
            .append("\n")
            .append("Now you have ")
            .append(this.taskList.getSize())
            .append(this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list.");
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}