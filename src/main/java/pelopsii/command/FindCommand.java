package pelopsii.command;

import pelopsii.exception.PelopsIIException;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 * Extends the Command abstract class.
 */
public class FindCommand extends Command{
    /**
     * The keyword to search for within task descriptions.
     */
    private String keyword;
    /**
     * Message displayed before the matching tasks.
     */
    private static final String FIND_TASK_MESSAGE = " Here are the matching tasks in your list:";

    /**
     * Constructs a FindCommand object with the provided input string.
     * Extracts the keyword from the input.
     *
     * @param input The user input string containing the find command and the keyword.
     * @throws PelopsIIException If the input is missing the keyword or is improperly formatted.
     */
    public FindCommand(String input) throws PelopsIIException {
        String[] action = input.split("find ");
        if (action.length == 1) {
            throw new PelopsIIException("Must specify keywords to find. Example find <keyword>");
        }
        this.keyword = action[1];
    }

    /**
     * Executes the find command, searches tasks for the keyword and displays the results.
     *
     * @throws PelopsIIException If there is an error executing the command.
     */
    @Override
    public void execute() throws PelopsIIException {
        String result = this.taskList.find(keyword);
        StringBuilder sb = new StringBuilder(FIND_TASK_MESSAGE).append("\n").append(result);
        this.ui.showMessageToUser(sb.toString());
    }
}