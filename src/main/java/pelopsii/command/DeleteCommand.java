package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.Task;

/**
 * Represents a command to delete a task by its index in the task list.
 * The command takes an index as input and removes the task at that position.
 * The input should specify a valid index, and errors are thrown if the index is invalid or the input is malformed.
 * 
 * This command performs input validation to ensure the correct number of arguments are provided, 
 * and that the specified index is a valid number. If the input does not meet the expected format, 
 * an InvalidCommandException is thrown.
 * 
 * Example usage:
 * <pre>
 * DeleteCommand deleteCommand = new DeleteCommand("delete 2");
 * </pre>
 */
public class DeleteCommand extends Command{
    private int pos;
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";

    /**
     * Constructs a DeleteCommand by parsing the user input to extract the task index.
     * The input must contain a single index to specify the task to be deleted.
     * 
     * @param input The user input containing the delete command and index.
     * @throws InvalidCommandException If the input format is incorrect or the index is not a valid number.
     */
    public DeleteCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new InvalidCommandException("You must specify an index when deleting a task");
        }
        if (action.length > 2) {
            throw new InvalidCommandException("You have specified too many parameters for the delete command");
        }
        try {
            pos = Integer.parseInt(action[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The position must be a valid number.");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        Task deleted = this.taskList.deleteTask(pos);
        assert deleted != null : "Deleted task should not be null";
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(DELETE_MESSAGE)
            .append("\n")
            .append(deleted)
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
