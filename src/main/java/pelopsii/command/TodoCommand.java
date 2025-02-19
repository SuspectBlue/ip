package pelopsii.command;

import pelopsii.exception.InvalidCommandException;
import pelopsii.exception.PelopsIIException;
import pelopsii.task.ToDo;

/**
 * Represents a command to create a ToDo task with a description.
 * The user input is parsed to extract the description of the ToDo task. 
 * If the input does not include a description, an InvalidCommandException is thrown.
 * 
 * Example usage:
 * <pre>
 * TodoCommand todoCommand = new TodoCommand("todo Buy groceries");
 * </pre>
 */
public class TodoCommand extends Command {

    private String description;
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    /**
     * Constructs a TodoCommand by parsing the user input to extract the task description.
     * The input must follow the format: "todo <description>", where the description specifies the task.
     * 
     * @param input The user input containing the todo command and task description.
     * @throws InvalidCommandException If the input format is incorrect or missing a description.
     */
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
        StringBuilder sb = new StringBuilder(ADD_TASK_MESSAGE)
            .append("\n")
            .append(todo)
            .append("\n")
            .append("Now you have ")
            .append(this.taskList.getSize())
            .append(this.taskList.getSize() == 1 ? " task in the list." : " tasks in the list.");
        this.response = sb.toString();
        this.ui.showMessageToUser(sb.toString());
    }

    @Override
    public String getResponse() {
        return this.response;
    }
    
}
