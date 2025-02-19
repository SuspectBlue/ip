package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;

/**
 * Represents a command to unmark a task as incomplete by its index in the task list.
 * The input specifies the index of the task to be unmarked as not done. 
 * If the input is invalid or the index is not a valid number, an InvalidCommandException is thrown.
 * 
 * Example usage:
 * <pre>
 * UnmarkCommand unmarkCommand = new UnmarkCommand("unmark 2");
 * </pre>
 */
public class UnmarkCommand extends Command{
    private int pos;
    private static final String UNMARK_MESSAGE = "Ok, I've marked this task as not done yet:";

    /**
     * Constructs an UnmarkCommand by parsing the user input to extract the task index to unmark.
     * The input must specify a valid task index.
     * 
     * @param input The user input containing the unmark command and task index.
     * @throws InvalidCommandException If the input format is incorrect or the task index is not a valid number.
     */
    public UnmarkCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length < 2) {
            throw new InvalidCommandException("You must specify the position of the task to unmark.");
        }
        try {
            pos = Integer.parseInt(action[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The position must be a valid number.");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        this.taskList.unmark(pos);
        this.storage.writeFile(taskList.getSaveData());
        this.response = UNMARK_MESSAGE + "\n" + this.taskList.getTaskByPosition(pos);
        this.ui.showMessageToUser(this.response);
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
