package command;

import Exceptions.PelopsIIException;
import Tasks.TaskList;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    public abstract void execute() throws PelopsIIException;

    public void setData(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    public boolean isExit() {
        return false;
    }
}
