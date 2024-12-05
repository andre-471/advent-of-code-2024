import java.util.*;

public class Day05Complex {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day05");
        int sectionDiv = data.indexOf("");
        ArrayList<String> subOne = new ArrayList<>(data.subList(0, sectionDiv));
        ArrayList<String> subTwo = new ArrayList<>(data.subList(sectionDiv + 1, data.size()));

        HashMap<Integer, HashSet<Integer>> afters = new HashMap<>();

        for (String rule : subOne) {
            String[] sRule = rule.split("\\|");
            int before = Integer.parseInt(sRule[0]);
            int after = Integer.parseInt(sRule[1]);
            afters.putIfAbsent(before, new HashSet<>());
            afters.get(before).add(after);
        }

        int sum = 0;

        for (String update : subTwo) {
            sum += evalUpdate(update, afters);
        }

        System.out.println(sum);
    }

    public static int evalUpdate(String update, HashMap<Integer, HashSet<Integer>> afters) {
        ArrayList<Integer> intUpdate = Arrays.stream(update.split(","))
                .mapToInt(Integer::parseInt)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (int i = 0; i < intUpdate.size(); i++) {
            if (!evalAfter(intUpdate, i, afters)) {
                return 0;
            }
        }

        return intUpdate.get(intUpdate.size() / 2);
    }

    public static boolean evalAfter(ArrayList<Integer> update, int index, HashMap<Integer, HashSet<Integer>> afters) {
        if (afters.get(update.get(index)) == null) {
            return true;
        }

        HashSet<Integer> required = new HashSet<>(afters.get(update.get(index)));

        for (int value : required) {
            if (update.contains(value) && update.indexOf(value) < index) {
                return false;
            }
        }

        return true;
    }
}
