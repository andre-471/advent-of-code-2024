import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day10 {
    public static void main(String[] args) {
        ArrayList<String> data = FileHelper.read("input/Day10");
        int[][] intData = new int[data.size()][];

        for (int i = 0; i < intData.length; i++) {
            intData[i] = data.get(i).chars().mapToObj(c -> (char) c).mapToInt(Character::getNumericValue).toArray();
        }

        int sum1 = 0;

        for (int i = 0; i < intData.length; i++) {
            for (int j = 0; j < intData[i].length; j++) {
                if (intData[i][j] == 0) {
                    sum1 += startSearch(i, j, intData);
                }
            }
        }

        System.out.println(sum1);

        int sum2 = 0;

        for (int i = 0; i < intData.length; i++) {
            for (int j = 0; j < intData[i].length; j++) {
                if (intData[i][j] == 0) {
                    sum2 += startSearchDistinct(i, j, intData);
                }
            }
        }

        System.out.println(sum2);
    }

    public static int startSearch(int y, int x, int[][] data) {
        HashSet<String> positions = new HashSet<>();
        search(y, x, -1, data, positions);

        return positions.size();
    }

    public static void search(int y, int x, int previous, int[][] data, HashSet<String> positions) {
        if (y < 0 || y >= data.length || x < 0 || x >= data[0].length || data[y][x] - 1 != previous) {
            return;
        }
        if (data[y][x] == 9) {
            positions.add(x + ", " + y);
        }
        search(y - 1, x, data[y][x], data, positions);
        search(y, x - 1, data[y][x], data, positions);
        search(y + 1, x, data[y][x], data, positions);
        search(y, x + 1, data[y][x], data, positions);
    }

    public static int startSearchDistinct(int y, int x, int[][] data) {
        return searchDistinct(y, x, -1, data);
    }

    public static int searchDistinct(int y, int x, int previous, int[][] data) {
        if (y < 0 || y >= data.length || x < 0 || x >= data[0].length || data[y][x] - 1 != previous) {
            return 0;
        }
        if (data[y][x] == 9) {
            return 1;
        }
        return searchDistinct(y - 1, x, data[y][x], data) +
                searchDistinct(y, x - 1, data[y][x], data) +
                searchDistinct(y + 1, x, data[y][x], data) +
                searchDistinct(y, x + 1, data[y][x], data);
    }
}
