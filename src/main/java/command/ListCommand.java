package command;

import Exceptions.PelopsIIException;

public class ListCommand extends Command {

    @Override
    public void execute() throws PelopsIIException {
        this.ui.showMessageToUser(this.taskList.toString());
    }
    
}
