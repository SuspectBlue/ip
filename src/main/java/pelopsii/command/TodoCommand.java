package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.ToDo;

public class TodoCommand extends Command {

    private String description;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    public TodoCommand(String input) throws InvalidCommandException {
        String[] action = input.split(" ");
        if (action.length == 1) {
            throw new InvalidCommandException("ToDo tasks require a description. For example: todo <description>");
        }
        this.description = input.substring(5);
    }

    @Override
    public void execute() throws PelopsIIException {
        ToDo todo = new ToDo(description);
        this.taskList.addTask(todo);
        this.storage.writeFile(taskList.getSaveData());
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE).append("\n")
                                                            .append(todo).append("\n")
                                                            .append("Now you have " + this.taskList.getSize() + (this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list."));
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}
