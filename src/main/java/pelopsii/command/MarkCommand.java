package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;

/**
 * Represents a command to mark a task as completed by its index in the task list.
 * The input specifies the index of the task to be marked as done. 
 * If the input is invalid or the index is not a valid number, an InvalidCommandException is thrown.
 * 
 * Example usage:
 * <pre>
 * MarkCommand markCommand = new MarkCommand("mark 2");
 * </pre>
 */
public class MarkCommand extends Command {
    private int pos;
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";

    /**
     * Constructs a MarkCommand by parsing the user input to extract the task index to mark as completed.
     * The input must specify a valid task index.
     * 
     * @param input The user input containing the mark command and task index.
     * @throws InvalidCommandException If the input format is incorrect or the task index is not a valid number.
     */
    public MarkCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length < 2) {
            throw new InvalidCommandException("You must specify the position of the task to mark.");
        }
        try {
            pos = Integer.parseInt(action[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The position must be a valid number.");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        this.taskList.mark(pos);
        this.storage.writeFile(taskList.getSaveData());
        this.response = MARK_MESSAGE + "\n" + this.taskList.getTaskByPosition(pos);
        this.ui.showMessageToUser(this.response);
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}
