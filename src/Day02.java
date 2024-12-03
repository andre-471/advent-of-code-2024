import java.util.ArrayList;
import java.util.Arrays;

public class Day02 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day02");

        int sum = (int) data.stream()
                // Convert all strings into arrays
                .map(str -> Arrays.stream(str.split(" ")).mapToInt(Integer::valueOf).toArray())
                // Removes reports that are unsafe
                .filter(array -> {
                    int sign = (int) Math.signum(array[1] - array[0]); // No edge case of delta == 0 since delta == 0 -> false

                    for (int i = 0; i < array.length - 1; i++) {
                        int delta = array[i + 1] - array[i];
                        int deltaSign = (int) Math.signum(delta);

                        if (Math.abs(delta) > 3 || Math.abs(delta) < 1 || sign != deltaSign) {
                            return false;
                        }
                    }

                    return true;
                })
                .count();

        System.out.println(sum);

    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/Day02");
//        System.out.println(data);

        int sum = (int) data.stream()
                // Convert all strings into arrays
                .map(str -> Arrays.stream(str.split(" "))
                        .mapToInt(Integer::valueOf)
                        .<ArrayList<Integer>>collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
                )
                // Removes reports that are unsafe
                .filter(arrayList -> {
                    if (isSafe(arrayList)) return true;

                    int removalIndex = 0;
                    while (removalIndex < arrayList.size()) {
                        ArrayList<Integer> arrayListRemoved = new ArrayList<>(arrayList);
                        arrayListRemoved.remove(removalIndex);

                        if (isSafe(arrayListRemoved)) return true;

                        removalIndex++;
                    }

                    return false;
                })
                .count();

        System.out.println(sum);

    }

    public static boolean isSafe(ArrayList<Integer> arrayList) {
        int sign = (int) Math.signum(arrayList.get(1) - arrayList.get(0));
        if (sign == 0) {
            return false;
        }

        for (int i = 0, len = arrayList.size(); i < len - 1; i++) {
            int delta = arrayList.get(i + 1) - arrayList.get(i);
            boolean order;

            if (sign == -1) {
                order = delta <= -1 && delta >= -3;
            } else {
                order = delta >= 1 && delta <= 3;
            }

            if (!order) {
                return false;
            }
        }

        return true;
    }

}
