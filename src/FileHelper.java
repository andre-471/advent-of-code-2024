import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    public static char[][] readTo2DArray(String path) {
        ArrayList<String> data = FileHelper.read(path);

        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            char[] row = data.get(i).toCharArray();
            grid[i] = row;
        }

        return grid;
    }

    public static ArrayList<Character> readToArraylist(String path) {
        return readToString(path).chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new));
    }
}
