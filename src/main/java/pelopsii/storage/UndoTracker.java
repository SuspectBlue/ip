package pelopsii.storage;

import pelopsii.exception.PelopsIIException;
import pelopsii.task.TaskList;

public class UndoTracker {
    private String prevCommand;
    private String prevData;

    public void savePrevState(String inputCommand, String saveData) {
        this.prevData = saveData;
        this.prevCommand = inputCommand;
        System.out.println(prevCommand);
        System.out.println(prevData);
    }

    public String undo(TaskList taskList, Storage storageFile) throws PelopsIIException {
        if (prevCommand == null || prevData == null ) {
            throw new PelopsIIException("No previous undo-able command");
        }
        storageFile.writeFile(prevData);
        taskList.overwriteData(prevData);

        return this.prevCommand;
    }

}
