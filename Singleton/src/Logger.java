import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance;
    private FileWriter writer;
    private String fileName;

    // Private constructor
    private Logger() {
        this.fileName = "default.log";
        openFile(fileName);
    }

    // Get singleton instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Open file
    private void openFile(String fileName) {
        try {
            writer = new FileWriter(fileName, true);
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
        }
    }

    // Set new file name
    public void setFileName(String fileName) {
        close();
        this.fileName = fileName;
        openFile(fileName);
    }

    // Write log message
    public void write(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Close the logger
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing file: " + e.getMessage());
        }
    }
}
