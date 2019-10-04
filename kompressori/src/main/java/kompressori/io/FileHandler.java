package kompressori.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    /**
     * Read file from provided path to string
     * @param path path to file
     * @return
     */
    public String readFile(String path) {
        StringBuilder data = new StringBuilder();

        try {
            Files.lines(Paths.get(path), StandardCharsets.UTF_8)
            .forEach(l -> data.append(l).append("\n"));
        } catch (IOException e) {
            System.out.println("There was an error reading the file: ");
            System.out.println(e.getMessage());
        }

        return data.toString();
    }

    public void writeObjectToFile(Object obj, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
        } catch (Exception e) {
            System.out.println("There was an error saving the file");
        }
    }

    public Object readObjectFromFile(String path) {
        Object obj = null;

        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Error reading file: ");
            System.out.println(e.getMessage());
        }

        return obj;
    }
}