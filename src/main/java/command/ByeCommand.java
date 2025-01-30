package command;

import Exceptions.PelopsIIException;

public class ByeCommand extends Command{

    private final static String byeMessage = "Bye. Hope to see you again soon!";
    
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() throws PelopsIIException {
        this.ui.showMessageToUser(byeMessage);
    }
}
