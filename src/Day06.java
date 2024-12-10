import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day06");

        char[][] grid = traverse(data);

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

        char[][] traversedGrid = createGrid(data);

        int[] coord = findStart(traversedGrid);
        int x = coord[0];
        int y = coord[1];

        char dir = 'U';

        int count = 0;

        HashSet<String> positions = new HashSet<>();

        while (inGrid(x, y, traversedGrid)) {
            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> dy = -1;
                case 'L' -> dx = -1;
                case 'D' -> dy = 1;
                case 'R' -> dx = 1;
            }

            if (inGrid(x + dx, y + dy, traversedGrid) && traversedGrid[y + dy][x + dx] != '#' && y + dy != coord[1] && x + dx != coord[0]) {

                char temp = traversedGrid[y + dy][x + dx];
                traversedGrid[y + dy][x + dx] = '#';
                if (ifLoop(x, y, dir, traversedGrid)) {
                    positions.add((x + dx) + "," + (y + dy));
                }

                traversedGrid[y + dy][x + dx] = temp;
            }


            if (inGrid(x + dx, y + dy, traversedGrid) && traversedGrid[y + dy][x + dx] == '#') {
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

        System.out.println(positions.size());
        System.out.println(positions);
    }
    
    public static char[][] createGrid(ArrayList<String> data) {
        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            grid[i] = data.get(i).toCharArray();
        }
        
        return grid;
    }

    public static int[] findStart(char[][] grid){
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

        return new int[]{x, y};
    }

    public static char[][] traverse(ArrayList<String> data) {
        char[][] grid = createGrid(data);
        
        int[] coord = findStart(grid);
        int x = coord[0];
        int y = coord[1];

        char dir = 'U';

        while (inGrid(x, y, grid)) {
            grid[y][x] = 'X';

            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> dy = -1;
                case 'L' -> dx = -1;
                case 'D' -> dy = 1;
                case 'R' -> dx = 1;
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

        return grid;
    }

    public static boolean ifLoop(int x, int y, char dir, char[][] grid) {
        HashMap<String, HashSet<Character>> gridDir = new HashMap<>();

        while (inGrid(x, y, grid)) {
            gridDir.putIfAbsent(x + "," + y, new HashSet<>());
            gridDir.get(x + "," + y).add(dir);

            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> dy = -1;
                case 'L' -> dx = -1;
                case 'D' -> dy = 1;
                case 'R' -> dx = 1;
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

            if (gridDir.getOrDefault(x + "," + y, new HashSet<>()).contains(dir)) {
                return true;
            }
        }

        return false;
    }
}
