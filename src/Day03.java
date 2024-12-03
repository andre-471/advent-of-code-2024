import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.readWithFiles("input/Day03");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int sum = 0;

        for (String line : data) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }

        System.out.println(sum);
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.readWithFiles("input/Day03");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int sum = 0;

        for (String line : data) {
            String[][] splitByDo = Arrays.stream(line.split("do\\(\\)"))
                    .map(array -> array.split("don't\\(\\)"))
                    .toArray(String[][]::new); // Each array is after a do (plus first one), so it can be used

            for (String[] splitByDont : splitByDo) {
                // Since split by don'ts, everything after first element will be after a don't, so ignore it
                Matcher matcher = pattern.matcher(splitByDont[0]);

                while (matcher.find()) {
                    sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                }
            }


        }

        System.out.println(sum);
    }
}
