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
import pelopsii.command.UnmarkCommand;
import pelopsii.exception.PelopsIIException;

public class Parser {

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
            default -> {
                return new HelpCommand();
            }
        }
    }
}
