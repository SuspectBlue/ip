package pelopsii.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private final BufferedReader br;

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");
    }

    public void showMessageToUser(String message) {
        System.out.println(message);
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }

    public void showLine() {
        System.out.println("_______");
    }

    public void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.err.println("Error: Failed to load. Please try again.");
    }

}
