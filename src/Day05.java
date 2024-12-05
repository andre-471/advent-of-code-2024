import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Day05 {
    public static void main(String[] args) {
//        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day05");
        int sectionDiv = data.indexOf("");
        ArrayList<String> subOne = new ArrayList<>(data.subList(0, sectionDiv));
        ArrayList<String> subTwo = new ArrayList<>(data.subList(sectionDiv + 1, data.size()));

        int sum = 0;

        for (String update : subTwo) {
            ArrayList<Integer> intUpdate = Arrays.stream(update.split(","))
                    .mapToInt(Integer::parseInt)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            sum += isOrdered(intUpdate, subOne);
        }

        System.out.println(sum);
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/Day05");
        int sectionDiv = data.indexOf("");
        ArrayList<String> subOne = new ArrayList<>(data.subList(0, sectionDiv));
        ArrayList<String> subTwo = new ArrayList<>(data.subList(sectionDiv + 1, data.size()));

        int sum = 0;

        for (String update : subTwo) {
            ArrayList<Integer> intUpdate = Arrays.stream(update.split(","))
                    .mapToInt(Integer::parseInt)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            if (isOrdered(intUpdate, subOne) == 0) {
                sort(intUpdate, subOne);
                sum += isOrdered(intUpdate, subOne);
            }
        }

        System.out.println(sum);
    }

    public static int isOrdered(ArrayList<Integer> update, ArrayList<String> rules) {
        for (String rule : rules) {
            String[] sRule = rule.split("\\|");
            int before = Integer.parseInt(sRule[0]);
            int after = Integer.parseInt(sRule[1]);

            if (update.contains(before) && update.contains(after) && update.indexOf(before) > update.indexOf(after)) {
                return 0;
            }
        }

        return update.get(update.size() / 2);
    }

    public static void sort(ArrayList<Integer> update, ArrayList<String> rules) {
        for (String rule : rules) {
            String[] sRule = rule.split("\\|");
            int before = Integer.parseInt(sRule[0]);
            int after = Integer.parseInt(sRule[1]);

            if (update.contains(before) && update.contains(after) && update.indexOf(before) > update.indexOf(after)) {
                int beforeI = update.indexOf(before);
                int afterI = update.indexOf(after);

                update.set(beforeI, after);
                update.set(afterI, before);

                sort(update, rules);
            }
        }
    }
}
