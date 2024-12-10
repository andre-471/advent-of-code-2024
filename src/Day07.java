import java.util.ArrayList;
import java.util.Arrays;

public class Day07 {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/test");
        int sum = 0;

        for (String line : data) {
            String[] hi = line.split(": ");
            ArrayList<Integer> hi2 = Arrays.stream(hi[1].split(" ")).mapToInt(Integer::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            if (testNumber(Integer.parseInt(hi[0]), hi2)) {
                sum += Integer.parseInt(hi[0]);
            }
        }

        System.out.println(sum);
    }

    public static boolean testNumber(int base, ArrayList<Integer> nums) {
        if (nums.isEmpty()) {
            return base == 0;
        }

        int num = nums.removeFirst();
        int divided = base / num;
        int subtracted = base - num;

        if (base % num != 0) {
            return testNumber(subtracted, new ArrayList<>(nums));
        } else {
            return testNumber(divided, new ArrayList<>(nums)) || testNumber(subtracted, new ArrayList<>(nums));
        }
    }

    public static void partTwo() {

    }
}
