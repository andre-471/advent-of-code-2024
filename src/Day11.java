import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
    public static HashMap<Long, HashMap<Integer, Long>> pastCalculations = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<String> data = FileHelper.read("input/Day11");
        ArrayList<Long> stones = Arrays.stream(data.getFirst().split(" ")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));

        int blinksNeeded = 75;
        long sum = 0;

        for (long stone : stones) {
            sum += blinkOnceMultipleTimes(stone, blinksNeeded);
        }



        System.out.println(sum);
    }

    public static long blinkOnceMultipleTimes(Long stone, int times) {
        if (times == 0) {
            return 1;
        }

        if (pastCalculations.get(stone) != null && pastCalculations.get(stone).get(times) != null) {
            return pastCalculations.get(stone).get(times);
        }

        long blinks;

        String strStone;
        if (stone == 0) {
            blinks = blinkOnceMultipleTimes(1L, times - 1);
        } else if (((strStone = String.valueOf(stone)).length() & 1) == 0){
            blinks = blinkOnceMultipleTimes(Long.valueOf(strStone.substring(0, strStone.length() / 2)), times - 1)
                    + blinkOnceMultipleTimes(Long.valueOf(strStone.substring(strStone.length() / 2)), times - 1);
        } else {
            blinks = blinkOnceMultipleTimes(stone * 2024L, times - 1);
        }

        pastCalculations.putIfAbsent(stone, new HashMap<>());
        pastCalculations.get(stone).put(times, blinks);

        return blinks;
    }
}
