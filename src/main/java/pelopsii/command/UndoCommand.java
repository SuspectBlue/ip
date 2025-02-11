package pelopsii.command;

import pelopsii.exception.PelopsIIException;

public class UndoCommand extends Command {

    private String prevCommand;

    @Override
    public void execute() throws PelopsIIException {
        this.prevCommand = this.undoTracker.undo(taskList, storage);
    }

    @Override
    public String getResponse() {
        return "Reverted Previous Command:\n" + prevCommand + "\n\nYour Updated Task List: \n" + taskList.toString();
    }
    
}
