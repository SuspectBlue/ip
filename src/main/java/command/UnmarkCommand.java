package command;

import Exceptions.PelopsIIException;

public class UnmarkCommand extends Command{
    private int pos;
    private static final String UNMARK_MESSAGE = "Ok, I've marked this task as not done yet:";

    public UnmarkCommand(String input) throws PelopsIIException {
        String[] action = input.split(" ");
        if (action.length < 2) {
            throw new PelopsIIException("You must specify the position of the task to unmark.");
        }
        try {
            pos = Integer.parseInt(action[1]);
        } catch (NumberFormatException e) {
            throw new PelopsIIException("The position must be a valid number.");
        }
    }

    @Override
    public void execute() throws PelopsIIException {
        this.taskList.unmark(pos);
        this.storage.writeFile(taskList.getSaveData());
        this.ui.showMessageToUser(UNMARK_MESSAGE + "\n" + this.taskList.getTaskByPosition(pos));
    }
}
