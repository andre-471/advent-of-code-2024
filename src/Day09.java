import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day09 {
    public static void main(String[] args) {
        partTwo();
    }

    public static void partOne() {
        ArrayList<Character> data = FileHelper.readToArraylist("input/Day09");
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

        while (disk.contains(null)) {
            Integer lastVal = disk.removeLast();
            if (lastVal != null && disk.contains(null)) {
                disk.set(disk.indexOf(null), lastVal);
            }
        }


        long sum = 0;

        for (int i = 0; i < disk.size(); i++) {
            sum += (long) i * disk.get(i);
        }

        System.out.println(sum);
    }

    public static void partTwo() {
        ArrayList<Character> data = FileHelper.readToArraylist("input/test");
        ArrayList<File> disk = new ArrayList<>();
        int id = 0;
        boolean empty = false;

        for (Character character : data) {
            int digit = Character.getNumericValue(character);
            if (empty) {
                disk.add(new File(null, digit));
            } else {
                disk.add(new File(id, digit));
                id++;
            }

            empty = !empty;
        }

        int i = disk.size() - 1;
        while (i >= 0) {
            File file = disk.get(i);
            for (int j = 0; j < i; j++) {
                File otherFile = disk.get(j);
                if (otherFile.number == null && file.size <= otherFile.size) {
                    disk.set(j, file);
                    if (file.size < otherFile.size) {
                        disk.add(j + 1, new File(null, otherFile.size - file.size));
                        i++;
                    }
                    break;
                }
            }

            i--;
        }

        ArrayList<Integer> actualDisk = new ArrayList<>();

        for (File file : disk) {
            for (int j = 0; j < file.size; j++) {
                actualDisk.add(file.number);
            }
        }

        System.out.println(actualDisk);
    }

    public static class File {
        Integer number;
        int size;

        public File(Integer number, int size) {
            this.number = number;
            this.size = size;
        }
    }
}
