import java.util.ArrayList;
import java.util.Arrays;

public class Day06 {
    public static void main(String[] args) {
        partOne();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day06");

        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            grid[i] = data.get(i).toCharArray();
        }

        int x = 0;
        int y = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    y = i;
                    x = j;
                }
            }
        }

        char dir = 'U';

        while (inGrid(x, y, grid)) {
            grid[y][x] = 'X';

            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> {
                    dy = -1;
                }
                case 'L' -> {
                    dx = -1;
                }
                case 'D' -> {
                    dy = 1;
                }
                case 'R' -> {
                    dx = 1;
                }
            }

            if (inGrid(x + dx, y + dy, grid) && grid[y + dy][x + dx] == '#') {
                dir = switch (dir) {
                    case 'U' -> 'R';
                    case 'R' -> 'D';
                    case 'D' -> 'L';
                    case 'L' -> 'U';
                    default -> throw new IllegalStateException("Unexpected value: " + dir);
                };
            } else {
                x += dx;
                y += dy;
            }
        }

        int count = 0;

        for (char[] row : grid) {
            for (char c : row) {
                if (c == 'X') {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static boolean inGrid(int x, int y, char[][] grid) {
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/Day06");

        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            grid[i] = data.get(i).toCharArray();
        }

        int x = 0;
        int y = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    y = i;
                    x = j;
                }
            }
        }

        char dir = 'U';
        int count = 0;

        while (inGrid(x, y, grid)) {
            grid[y][x] = 'X';

            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> {
                    dy = -1;
                }
                case 'L' -> {
                    dx = -1;
                }
                case 'D' -> {
                    dy = 1;
                }
                case 'R' -> {
                    dx = 1;
                }
            }

            if (inGrid(x + dx, y + dy, grid) && grid[y + dy][x + dx] == '#') {
                dir = switch (dir) {
                    case 'U' -> 'R';
                    case 'R' -> 'D';
                    case 'D' -> 'L';
                    case 'L' -> 'U';
                    default -> throw new IllegalStateException("Unexpected value: " + dir);
                };
            } else {
                x += dx;
                y += dy;
            }
        }
    }
}
