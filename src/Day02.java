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
                        .toArray())
                .collect(Collectors.toCollection(ArrayList::new));



        Array list<int[]> sum = data.stream()
                .map(str -> Arrays.stream(str.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .filter(array -> {
int sign = (array[1] - array[0])/Math.abs(array[1] - array[0]);
for (int i = 0; i < array.length; i++) {
int change = array[i + 1] - array[i];
int changeSign = change/Math.abs(change);
if (Math.abs(change) > 3 || change
.collect(Collectors.toCollection(ArrayList::new));


    }

}
