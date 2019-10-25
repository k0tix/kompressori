package kompressori.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A simple file handler for writing and reading files
 */
public class FileHandler {

    /**
     * Writes a byte array to a specified file
     * @param filepath
     * @param data
     */
    public void writeFile(String filepath, byte[] data) {
        try {
            Files.write(Paths.get(filepath), data);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads a byte array from a specified filepath
     * @param filepath
     * @return
     */
    public byte[] readFile(String filepath) {
        byte[] data = new byte[0];

        try {
            data = Files.readAllBytes(Paths.get(filepath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return data;
    }
}