import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static void main(String[] args) {
        partOne();
        partTwov1();
        partTwov2();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day03");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int sum = 0;

        for (String line : data) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }

        ConsoleHelper.printDayOne(sum);
    }

    public static void partTwov1() {
        String data = FileHelper.readToString("input/Day03");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int sum = 0;

        String[][] splitByDo = Arrays.stream(data.split("do\\(\\)"))
                .map(array -> array.split("don't\\(\\)"))
                .toArray(String[][]::new); // Each array is after a do (plus first one), so it can be used

        for (String[] splitByDont : splitByDo) {
            // Since split by don'ts, everything after first element will be after a don't, so ignore it
            Matcher matcher = pattern.matcher(splitByDont[0]);

            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }


        ConsoleHelper.printDayTwo(sum);
    }

    public static void partTwov2() {
        String data = FileHelper.readToString("input/Day03");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))");
        Matcher matcher = pattern.matcher(data);

        int sum = 0;
        boolean enabled = true;

        while (matcher.find()) {
            if (matcher.group(3) != null) { // matches do
                enabled = true;
            } else if (matcher.group(4) != null) { // matches don't
                enabled = false;
            } else if (enabled) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }


        ConsoleHelper.printDayTwo(sum);
    }
}
