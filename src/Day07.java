import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
    public static void main(String[] args) {
        partOne();
        partTwo();
//        System.out.println(unconcatenate(100, 10));
//        System.out.println(testNumberWithConcatenation(11916446, new ArrayList<>(List.of(119L, 16L, 445L))));
//        System.out.println(testNumberWithConcatenation(80, new ArrayList<>(List.of(6L, 7L, 3L, 5L, 2L))));
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day07");
        long sum = 0;

        for (String line : data) {
            String[] hi = line.split(": ");
            ArrayList<Long> hi2 = Arrays.stream(hi[1].split(" ")).mapToLong(Long::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            if (testNumber(Long.parseLong(hi[0]), hi2)) {
                sum += Long.parseLong(hi[0]);
            }
        }

        System.out.println(sum);
    }

    public static boolean testNumberWithConcatenation(long base, ArrayList<Long> nums) {
        if (base < 0 || (base == 0 && !nums.isEmpty())) {
            return false;
        }

        if (nums.isEmpty()) {
            return base == 0;
        }


        nums = new ArrayList<>(nums);
        long num = nums.removeLast();
        long divided = base % num == 0 ? base / num : -1;
        long subtracted = base - num;
        long unconcatenated = unconcatenate(base, num);

        return testNumberWithConcatenation(divided, nums) || testNumberWithConcatenation(unconcatenated, nums) || testNumberWithConcatenation(subtracted, nums);
    }

    public static boolean testNumber(long base, ArrayList<Long> nums) {
        if (base < 0 || (base == 0 && !nums.isEmpty())) {
            return false;
        }

        if (nums.isEmpty()) {
            return base == 0;
        }


        nums = new ArrayList<>(nums);
        long num = nums.removeLast();
        long divided = base % num == 0 ? base / num : -1;
        long subtracted = base - num;

        return testNumber(divided, nums) || testNumber(subtracted, nums);
    }


    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/Day07");
        long sum = 0;

        for (String line : data) {
            String[] hi = line.split(": ");
            ArrayList<Long> hi2 = Arrays.stream(hi[1].split(" ")).mapToLong(Long::valueOf).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            if (testNumberWithConcatenation(Long.parseLong(hi[0]), hi2)) {
                sum += Long.parseLong(hi[0]);
            }
        }

        System.out.println(sum);
    }

    public static long unconcatenate(long num, long operator) {
        String strNum = String.valueOf(num);
        String strOperator = String.valueOf(operator);
        int index = strNum.lastIndexOf(strOperator);


        if (index > 0 && index == strNum.length() - strOperator.length()) {  // so num != operator
            return Long.parseLong(strNum.substring(0, index));
        } else {
            return -1;
        }
    }
}
