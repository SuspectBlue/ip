package pelopsii;
import java.io.IOException;

import pelopsii.storage.Storage;
import pelopsii.storage.UndoTracker;
import pelopsii.task.TaskList;
import pelopsii.ui.Ui;
import pelopsii.parser.Parser;
import pelopsii.command.Command;
import pelopsii.exception.PelopsIIException;

/**
 * The main class for the Pelops II task management application.
 * Manages the application's lifecycle, including storage, task list, user interface, and command execution.
 */
public class PelopsII {

    /**
     * The Storage object for handling file operations.
     */
    private Storage storageFile;
    /**
     * The Undo tracker for handling undo operations.
     */
    private UndoTracker undoTracker;
    /**
     * The TaskList object for managing tasks.
     */
    private TaskList taskList;
    /**
     * The Ui object for handling user interface interactions.
     */
    private Ui ui;

    /**
     * Constructs a PelopsII object with the specified file path.
     *
     * @param filePath The path to the data storage file.
     */
    public PelopsII(String filePath) {
        ui = new Ui();
        storageFile = new Storage(filePath);
        undoTracker = new UndoTracker();

        try {
            storageFile.load("PelopsII.txt");
            taskList = new TaskList(storageFile.readFile());
            assert taskList != null : "TaskList should not be null";
        } catch (PelopsIIException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Pelops II application, handling user input and executing commands.
     *
     * @throws IOException If an I/O error occurs during command processing.
     */
    public void run() throws IOException{
    
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.setData(taskList, ui, storageFile, undoTracker);
                c.execute();
                isExit = c.isExit();
            } catch (PelopsIIException e) {
                System.out.println(e.getMessage());
            }
        }
        // System.out.println("Running Java version: " + System.getProperty("java.version"));
    }

    public String getResponse(String input) {
        try {
            String prevData = taskList.getSaveData();
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";
            c.setData(taskList, ui, storageFile, undoTracker);
            c.execute();
            if(Command.isUndoableCommand(c)) {
                undoTracker.savePrevState(input, prevData);
            }
            return c.getResponse();
        } catch (PelopsIIException e) {
            return "Error: " + e.getMessage();
        }
    }
}