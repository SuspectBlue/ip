package pelopsii.command;

import pelopsii.exception.PelopsIIException;

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
