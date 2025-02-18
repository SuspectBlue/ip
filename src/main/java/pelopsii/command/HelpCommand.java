package pelopsii.command;

import pelopsii.exception.PelopsIIException;

public class HelpCommand extends Command {

    @Override
    public void execute() throws PelopsIIException {
        StringBuilder sb = new StringBuilder();
        sb.append("Please use one of the following commands:").append("\n");
        sb.append("bye").append("\n");
        sb.append("list").append("\n");
        sb.append("todo").append("\n");
        sb.append("deadline").append("\n");
        sb.append("event").append("\n");
        sb.append("mark").append("\n");
        sb.append("unmark").append("\n");
        sb.append("delete").append("\n");
        sb.append("undo");
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}
