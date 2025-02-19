package pelopsii.command;

import pelopsii.exception.PelopsIIException;
import pelopsii.storage.Storage;
import pelopsii.storage.UndoTracker;
import pelopsii.task.TaskList;
import pelopsii.ui.Ui;

/**
 * Abstract base class for all command objects in the Pelops II application.
 * Provides a common interface for executing commands and managing data access.
 */
public abstract class Command {
    /**
     * The TaskList object used to manage tasks.
     */
    protected TaskList taskList;
    /**
     * The Storage object used for file operations.
     */
    protected Storage storage;
    /**
     * The UndoTracker object used for undo operations.
     */
    protected UndoTracker undoTracker;
    /**
     * The Ui object used for user interface interactions.
     */
    protected Ui ui;
        /**
     * The response to send to users
     */
    protected String response;

    /**
     * Abstract method to execute the command. This method will be implemented by concrete command classes
     * to define specific behavior when the command is executed.
     * It typically involves interacting with the task list, storage and ui
     * to perform the desired action.
     * 
     * The method may throw exceptions if the command execution encounters an error, such as invalid data or system failure.
     * 
     * @throws PelopsIIException If an error occurs during the execution of the command.
     */
    public abstract void execute() throws PelopsIIException;

    public abstract String getResponse();

    /**
     * Sets the necessary data for the command to execute.
     *
     * @param taskList The TaskList object.
     * @param ui       The Ui object.
     * @param storage  The Storage object.
     */
    public void setData(TaskList taskList, Ui ui, Storage storage, UndoTracker undoTracker) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.undoTracker = undoTracker;
    }

    /**
     * Returns whether the command is an exit command.
     * Defaults to false, can be overridden by subclasses.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks whether a given command is undoable.
     *
     * @param c The command to be checked.
     * @return {@code true} if the command is undoable, {@code false} otherwise.
     */
    public static boolean isUndoableCommand(Command c) {
        return c instanceof DeadlineCommand || 
            c instanceof DeleteCommand ||
            c instanceof EventCommand ||
            c instanceof MarkCommand ||
            c instanceof TodoCommand ||
            c instanceof UnmarkCommand ||
            c instanceof UndoCommand;
    }
}