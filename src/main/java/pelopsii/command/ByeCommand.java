package pelopsii.command;

import pelopsii.exception.PelopsIIException;

public class ByeCommand extends Command{

    private final static String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() throws PelopsIIException {
        this.ui.showMessageToUser(BYE_MESSAGE);
    }

    @Override
    public String getResponse() {
        return BYE_MESSAGE;
    }
}
