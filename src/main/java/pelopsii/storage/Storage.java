package pelopsii.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import pelopsii.exception.PelopsIIException;

/**
 * Handles storage operations for the Pelops II application,
 * including loading data from and saving data to a file.
 */
public class Storage {
    /**
     * The path to the file where data is stored.
     */
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data file, creating it if it doesn't exist.
     *
     * @return The File object representing the loaded or created file.
     * @throws PelopsIIException If there is an error loading or creating the file.
     */
    public File load(String fileName) throws PelopsIIException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file = new File(directory, fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            return file;
        } catch (IOException error) {
            throw new PelopsIIException("Failed to load or create the file. Please check file path permissions and ensure the 'data' directory is accessible.");
        }
    }

    /**
     * Writes the given task data to the data file.
     *
     * @param taskData The task data to be written to the file.
     */
    public void writeFile(String taskData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "/PelopsII.txt"))) {
                writer.write(taskData);
        } catch (IOException error) {
            System.out.println("Error writing to the file");
        }
    }

    /**
     * Reads the content of the data file.
     *
     * @return The content of the file as a String, or an empty string if an error occurs.
     */
    public String readFile() {
        try (BufferedReader filereader = new BufferedReader(new FileReader(filePath + "/PelopsII.txt"))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = filereader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException error) {
            System.out.println("Error reading the file");
        }
        return "";
    }
}