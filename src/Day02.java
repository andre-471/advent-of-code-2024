import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day02 {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.readWithFiles("input/test");
        System.out.println(data);

        ArrayList<int[]> parsedData = data.stream().
                map(str -> Arrays.stream(str.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray()
                ).collect(Collectors.toCollection(ArrayList::new));
    }

}
