import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day09 {
    public static void main(String[] args) {
        ArrayList<Character> data = FileHelper.readToArraylist("input/test");
        ArrayList<Integer> disk = new ArrayList<>();
        int id = 0;
        boolean empty = false;

        for (Character character : data) {
            int digit = Character.getNumericValue(character);
            if (empty) {
                for (int i = 0; i < digit; i++) {
                    disk.add(null);
                }
            } else {
                for (int i = 0; i < digit; i++) {
                    disk.add(id);
                }
                id++;
            }

            empty = !empty;
        }
        System.out.println(disk);

        while (disk.contains(null)) {
            Integer lastVal = disk.removeLast();
            if (lastVal != null && disk.contains(null)) {
                disk.set(disk.indexOf(null), lastVal);
            }
        }


        System.out.println(disk);
        int sum = 0;

        for (int i = 0; i < disk.size(); i++) {
//            if (disk.get(i)) {
//
//            }
            sum += i * disk.get(i);
        }

        System.out.println(sum);
    }
}
