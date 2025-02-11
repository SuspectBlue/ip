package pelopsii.parser;

import pelopsii.command.ByeCommand;
import pelopsii.command.Command;
import pelopsii.command.DeadlineCommand;
import pelopsii.command.DeleteCommand;
import pelopsii.command.EventCommand;
import pelopsii.command.FindCommand;
import pelopsii.command.HelpCommand;
import pelopsii.command.ListCommand;
import pelopsii.command.MarkCommand;
import pelopsii.command.TodoCommand;
import pelopsii.command.UndoCommand;
import pelopsii.command.UnmarkCommand;
import pelopsii.exception.PelopsIIException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input string and returns a Command object.
     *
     * @param input The user input string.
     * @return A Command object corresponding to the user input.
     * @throws PelopsIIException If the input cannot be parsed into a valid command.
     */
    public static Command parse(String input) throws PelopsIIException {
        String[] action = input.split(" ");
        String command = action[0].toLowerCase();
        switch (command) {
            case "bye" -> {
                return new ByeCommand();
            }
            case "list" -> {
                return new ListCommand();
            }
            case "mark" -> {
                return new MarkCommand(input);
            }
            case "unmark" -> {
                return new UnmarkCommand(input);
            }
            case "todo" -> {
                return new TodoCommand(input);
            }
            case "deadline" -> {
                return new DeadlineCommand(input);
            }
            case "event" -> {
                return new EventCommand(input);
            }
            case "delete" -> {
                return new DeleteCommand(input);
            }
            case "find" -> {
                return new FindCommand(input);
            }
            case "undo" -> {
                return new UndoCommand();
            }
            default -> {
                return new HelpCommand();
            }
        }
    }
}