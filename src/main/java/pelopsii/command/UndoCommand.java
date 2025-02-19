package pelopsii.command;

import pelopsii.exception.PelopsIIException;

/**
 * Represents a command to undo the previous command executed in the application.
 * This command reverts the task list to its previous state by calling the undo method on the undo tracker.
 * It retrieves the previously executed command and displays the updated task list to the user.
 */
public class UndoCommand extends Command {

    
    private String prevCommand;

    /**
     * Executes the undo command by calling the undo method on the undo tracker.
     * This method retrieves the previously executed command and applies it to the task list.
     * 
     * @throws PelopsIIException If an error occurs during the undo operation.
     */
    @Override
    public void execute() throws PelopsIIException {
        this.prevCommand = this.undoTracker.undo(taskList, storage);
    }

    @Override
    public String getResponse() {
        return "Reverted Previous Command:\n" + prevCommand + "\n\nYour Updated Task List: \n" + taskList.toString();
    }
    
}
