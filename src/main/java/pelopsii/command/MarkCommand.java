package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;

public class MarkCommand extends Command {
    private int pos;
    private static final String MARK_MESSAGE = "Nice! I've marked this task as done:";

    public MarkCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length < 2) {
            throw new InvalidCommandException("You must specify the position of the task to mark.");
        }
        try {
            pos = Integer.parseInt(action[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("The position must be a valid number.");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        this.taskList.mark(pos);
        this.storage.writeFile(taskList.getSaveData());
        this.response = MARK_MESSAGE + "\n" + this.taskList.getTaskByPosition(pos);
        this.ui.showMessageToUser(this.response);
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}
