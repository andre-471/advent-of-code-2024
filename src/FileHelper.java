import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHelper {
    public static ArrayList<String> read(String path) {
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Unable to read from " + path);
        }

        return new ArrayList<>();
    }

    public static BufferedReader returnBuffer(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    public static String readToString(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Unable to read from " + path);
        }

        return "";
    }
}
