package pelopsii.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import pelopsii.exception.PelopsIIException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File load() throws PelopsIIException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file = new File(directory, "PelopsII.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            return file;
        } catch (IOException error) {
            throw new PelopsIIException("Failed to load or create the file. Please check file path permissions and ensure the 'data' directory is accessible.");
        }
    }

    public void writeFile(String taskData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "/PelopsII.txt"))) {
                writer.write(taskData);
        } catch (IOException error) {
            System.out.println("Error writing to the file");
        }
    }

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
