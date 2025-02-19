package pelopsii.storage;

import pelopsii.exception.PelopsIIException;
import pelopsii.task.TaskList;

/**
 * The UndoTracker class manages undo operations by storing the previous command and the associated data state.
 * It allows for reverting the system to the previous state by restoring the stored data.
 * This is useful for maintaining the integrity of the task list and ensuring that users can revert changes if necessary.
 * The undo operation restores both the task list and any other related data to its previous state, allowing users to undo their previous action.
 */
public class UndoTracker {
    private String prevCommand;
    private String prevData;

    /**
     * Saves the previous command and associated data state.
     *
     * @param inputCommand The command that was executed.
     * @param saveData The data state before the command was executed.
     */
    public void savePrevState(String inputCommand, String saveData) {
        this.prevData = saveData;
        this.prevCommand = inputCommand;
        System.out.println(prevCommand);
        System.out.println(prevData);
    }

    /**
     * Reverts the system to the previous state by restoring stored data.
     *
     * @param taskList The {@link TaskList} to be restored.
     * @param storageFile The {@link Storage} file handler for persisting data.
     * @return The previously executed command.
     * @throws PelopsIIException If no undo-able command is available.
     */
    public String undo(TaskList taskList, Storage storageFile) throws PelopsIIException {
        if (prevCommand == null || prevData == null ) {
            throw new PelopsIIException("No previous undo-able command");
        }
        storageFile.writeFile(prevData);
        taskList.overwriteData(prevData);

        return this.prevCommand;
    }
}
