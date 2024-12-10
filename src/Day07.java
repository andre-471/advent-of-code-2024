import java.util.ArrayList;
import java.util.Arrays;

public class Day07 {
    public static void main(String[] args) {
        partOne();
        partTwo();
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
        if (nums.isEmpty()) {
            return base == 0;
        }

        nums = new ArrayList<>(nums);
        long num = nums.removeLast();
        long divided = base / num;
        long subtracted = base - num;
        long unconcatenated = unconcatenate(base, num);


        if (base % num != 0 && unconcatenated == -1) {
            return testNumber(subtracted, nums);
        } else if (base % num == 0 && unconcatenated == -1) {
            return testNumber(divided, nums) || testNumber(subtracted, nums);
        } else if (base % num != 0 && unconcatenated != -1) {
            return testNumber(unconcatenated, nums) || testNumber(subtracted, nums);
        } else {
            return testNumber(divided, nums) || testNumber(unconcatenated, nums) || testNumber(subtracted, nums);
        }
    }

    public static boolean testNumber(long base, ArrayList<Long> nums) {
        if (nums.isEmpty()) {
            return base == 0;
        }

        nums = new ArrayList<>(nums);
        long num = nums.removeLast();
        long divided = base / num;
        long subtracted = base - num;

        if (base % num != 0) {
            return testNumber(subtracted, nums);
        } else {
            return testNumber(divided, nums) || testNumber(subtracted, nums);
        }
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

        if (index == strNum.length() - strOperator.length()) {
            return Long.parseLong(strNum.substring(0, index));
        } else {
            return -1;
        }
    }
}
