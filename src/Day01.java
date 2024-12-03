import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day01 {
    public static void main(String[] args) throws IOException {
        partOne();
        partTwo();
    }

    public static void partOne() {
        // reading and parsing
        ArrayList<String> data = FileHelper.read("input/Day01");
        ArrayList<Integer> leftSide = new ArrayList<>();
        ArrayList<Integer> rightSide = new ArrayList<>();
        data.forEach(str -> {
            int[] bothSides = Arrays.stream(str.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            leftSide.add(bothSides[0]);
            rightSide.add(bothSides[1]);
        });

        // sorting the lists
        leftSide.sort(null);
        rightSide.sort(null);

        // summing based on order
        int sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += Math.abs(leftSide.get(i) - rightSide.get(i));
        }

        System.out.println("Part 1: " + sum);
    }

    public static void partTwo() throws IOException {
        // reading and data structuring it
        HashMap<Integer, Integer> leftSideCount = new HashMap<>();
        HashMap<Integer, Integer> rightSideCount = new HashMap<>();

        try (BufferedReader br = FileHelper.returnBuffer("input/Day01")) {
            String line;

            while ((line = br.readLine()) != null) {
                int[] bothSides = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                leftSideCount.put(bothSides[0], leftSideCount.getOrDefault(bothSides[0], 0) + 1);
                rightSideCount.put(bothSides[1], rightSideCount.getOrDefault(bothSides[1], 0) + 1);
            }
        }


        // calculating score based on criteria
        int score = 0;

        for (int num : leftSideCount.keySet()) {
            int leftCount = leftSideCount.get(num);
            int rightCount = rightSideCount.getOrDefault(num, 0);

            score += (num * rightCount) * leftCount; // leftCount if the num is on the left side multiple times
        }

        System.out.println("Part 2: " + score);
    }
}
