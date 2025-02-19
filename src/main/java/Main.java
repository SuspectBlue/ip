import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pelopsii.PelopsII;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private PelopsII pelopsii = new PelopsII("data");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Pelops II");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            assert scene != null : "Scene should be instanciated and not null";
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPelopsii(pelopsii);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}