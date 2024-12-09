import java.util.ArrayList;
import java.util.Arrays;

public class Day07 {
    public static void main(String[] args) {

    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/test");

        for (String line : data) {
            String[] hi = line.split(": ");
            ArrayList<Integer> hi2 = Arrays.stream(hi[1].split(" ")).mapToInt(Integer::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        }
    }

    public static boolean testNumber(int base, int num) {
        int divided = base / num;
        int subtracted = base - num;

        if (base % num != 0) {
            return testNumber(subtracted, num);
        } else {
            return testNumber(divided, )
        }
    }

    public static void partTwo() {

    }
}
