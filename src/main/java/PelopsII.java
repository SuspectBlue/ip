import java.io.IOException;
import Exceptions.PelopsIIException;
import Tasks.TaskList;
import storage.Storage;
import ui.Ui;
import parser.Parser;
import command.Command;

public class PelopsII {

    private Storage storageFile;
    private TaskList taskList;
    private Ui ui;

    public PelopsII(String filePath) {
        ui = new Ui();
        storageFile = new Storage(filePath);
        try {
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

    public static void main(String[] args) throws IOException {
        new PelopsII("./src/main/java/data").run();
    }
}
