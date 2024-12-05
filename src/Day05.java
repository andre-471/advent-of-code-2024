import java.util.*;

public class Day05 {
    public static void main(String[] args) {
//        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/test");
        int sectionDiv = data.indexOf("");
        ArrayList<String> subOne = new ArrayList<>(data.subList(0, sectionDiv));
        ArrayList<String> subTwo = new ArrayList<>(data.subList(sectionDiv + 1, data.size()));

        System.out.println(subOne);
        System.out.println(subTwo);

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
        ArrayList<Integer> intUpdate = Arrays.stream(update.split(",")).mapToInt(Integer::parseInt).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

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

        for (int i = index + 1; i < update.size() ; i++) {
            required.remove(update.get(i));
        }

        for (int notPrinted : required) {
            if (update.contains(notPrinted)) {
                return false;
            }
        }

        return true;
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/test");
        int sectionDiv = data.indexOf("");
        ArrayList<String> subOne = new ArrayList<>(data.subList(0, sectionDiv));
        ArrayList<String> subTwo = new ArrayList<>(data.subList(sectionDiv + 1, data.size()));

        System.out.println(subOne);
        System.out.println(subTwo);

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
            if (evalUpdate(update, afters) == 0) {
                sum += evalUpdateTwo(update, afters);
            }
        }

        System.out.println(sum);
    }

    public static int evalUpdateTwo(String update, HashMap<Integer, HashSet<Integer>> afters) {
        ArrayList<Integer> intUpdate = Arrays.stream(update.split(",")).mapToInt(Integer::parseInt).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (int i = 0; i < intUpdate.size(); i++) {
            evalAfterTwo(intUpdate, i, afters);
        }
        System.out.println(intUpdate.get(intUpdate.size() / 2));
        return intUpdate.get(intUpdate.size() / 2);
    }

    public static boolean evalAfterTwo(ArrayList<Integer> update, int index, HashMap<Integer, HashSet<Integer>> afters) {
        if (afters.get(update.get(index)) == null) {
            return true;
        }

        HashSet<Integer> required = new HashSet<>(afters.get(update.get(index)));

        for (int i = index + 1; i < update.size() ; i++) {
            required.remove(update.get(i));
        }

        for (int notPrinted : required) {
            if (update.contains(notPrinted)) {
                int uh = indexOfClosetToOtherIndex(notPrinted, index, update);

                int temp = update.get(index);
                update.set(index, notPrinted);
                update.set(uh, temp);
                evalAfterTwo(update, index, afters);
            }
        }

        return true;
    }

    public static int indexOfClosetToOtherIndex(int value, int index, ArrayList<Integer> integers) {
        int lastIndex = -1;

        for (int i = 0; i < index; i++) {
            if (integers.get(i) == value) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }
}
