package parser;

import Exceptions.PelopsIIException;
import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

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
            default -> {
                return new HelpCommand();
            }
        }
    }
}
