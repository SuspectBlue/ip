package pelopsii.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles user interface interactions for the Pelops II application.
 * Provides methods for displaying messages, reading user input, and showing errors.
 */
public class Ui {
    /**
     * The BufferedReader used to read user input from the console.
     */
    private final BufferedReader br;

    /**
     * Constructs a Ui object, initializing the BufferedReader for console input.
     */
    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessageToUser(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command from the user's input.
     *
     * @return The command entered by the user.
     * @throws IOException If an I/O error occurs while reading the input.
     */
    public String readCommand() throws IOException {
        return br.readLine();
    }

    /**
     * Displays a line separator in the console.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    /**
     * Displays an error message indicating a loading failure.
     */
    public void showLoadingError() {
        System.err.println("Error: Failed to load. Please try again.");
    }

}