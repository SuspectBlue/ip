package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.Task;

public class DeleteCommand extends Command{
    private int pos;
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";

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
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(DELETE_MESSAGE).append("\n")
                                                            .append(deleted).append("\n")
                                                            .append("Now you have " + this.taskList.getSize() + (this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list."));
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
