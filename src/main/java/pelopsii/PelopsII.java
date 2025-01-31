package pelopsii;
import java.io.IOException;

import pelopsii.storage.Storage;
import pelopsii.task.TaskList;
import pelopsii.ui.Ui;
import pelopsii.parser.Parser;
import pelopsii.command.Command;
import pelopsii.exception.PelopsIIException;

public class PelopsII {

    private Storage storageFile;
    private TaskList taskList;
    private Ui ui;

    public PelopsII(String filePath) {
        ui = new Ui();
        storageFile = new Storage(filePath);
        try {
            storageFile.load();
            taskList = new TaskList(storageFile.readFile());
        } catch (PelopsIIException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() throws IOException{
    
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.setData(taskList, ui, storageFile);
                c.execute();
                isExit = c.isExit();
            } catch (PelopsIIException e) {
                System.out.println(e.getMessage());
            }
        }
        // System.out.println("Running Java version: " + System.getProperty("java.version"));
    }
}
