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
     * Abstract method to execute the command.
     *
     * @throws PelopsIIException If there is an error during command execution.
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