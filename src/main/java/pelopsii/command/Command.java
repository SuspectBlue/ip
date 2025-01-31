package pelopsii.command;

import pelopsii.exception.PelopsIIException;
import pelopsii.storage.Storage;
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
     * The Ui object used for user interface interactions.
     */
    protected Ui ui;

    /**
     * Abstract method to execute the command.
     *
     * @throws PelopsIIException If there is an error during command execution.
     */
    public abstract void execute() throws PelopsIIException;

    /**
     * Sets the necessary data for the command to execute.
     *
     * @param taskList The TaskList object.
     * @param ui       The Ui object.
     * @param storage  The Storage object.
     */
    public void setData(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
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
}