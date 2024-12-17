import java.util.ArrayList;
import java.util.Arrays;

public class Day10 {
    public static void main(String[] args) {
        ArrayList<String> data = FileHelper.read("input/test");
        int[][] intData = new int[data.size()][];

        for (int i = 0; i < intData.length; i++) {
            intData[i] = data.get(i).chars().mapToObj(c -> (char) c).mapToInt(Character::getNumericValue).toArray();
        }

        int sum = 0;

        for (int i = 0; i < intData.length; i++) {
            for (int j = 0; j < intData[i].length; j++) {
                if (intData[i][j] == 0) {
                    sum += search(i, j, -1, intData);
                }
            }
        }

        System.out.println(sum);
    }

    public static int search(int y, int x, int previous, int[][] data) {
        if (y < 0 || y >= data.length || x < 0 || x >= data[0].length || data[y][x] - 1 != previous) {
            return 0;
        }
        if (data[y][x] == 9) {
            System.out.println(previous);
            System.out.println(x + ", " + y);
            return 1;
        }

        return search(y-1, x, data[y][x], data) +
                search(y, x-1, data[y][x], data) +
                search(y+1, x, data[y][x], data) +
                search(y, x+1, data[y][x], data);
    }
}
