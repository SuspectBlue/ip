package pelopsii.command;

import pelopsii.exception.PelopsIIException;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves all tasks and displays them to the user.
 * It overrides the execute and getResponse methods to provide functionality for showing the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute() throws PelopsIIException {
        this.ui.showMessageToUser(this.taskList.toString());
    }

    @Override
    public String getResponse() {
        return this.taskList.toString();
    }
    
}
