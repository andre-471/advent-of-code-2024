import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day06 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/test");

        char[][] grid = traverse(data);

        int count = 0;

        for (char[] row : grid) {
            for (char c : row) {
                if (c == 'X') {
                    count++;
                }
            }
        }
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(count);
    }

    public static boolean inGrid(int x, int y, char[][] grid) {
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/test");

        char[][] traversedGrid = createGrid(data);
        HashSet<?>[][] gridDir = new HashSet<?>[traversedGrid.length][];
        for (int i = 0; i < data.size(); i++) {
            gridDir[i] = new HashSet<?>[traversedGrid[0].length];
        }

        int[] coord = findStart(traversedGrid);
        int x = coord[0];
        int y = coord[1];

        char dir = 'U';

        while (inGrid(x, y, traversedGrid)) {
            traversedGrid[y][x] = 'X';

            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> dy = -1;
                case 'L' -> dx = -1;
                case 'D' -> dy = 1;
                case 'R' -> dx = 1;
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

        char[][] grid = createGrid(data);

        x = coord[0];
        y = coord[1];

        char dir = 'U';

        int count = 0;

        while (inGrid(x, y, traversedGrid)) {
            int dx = 0;
            int dy = 0;

            switch (dir) {
                case 'U' -> dy = -1;
                case 'L' -> dx = -1;
                case 'D' -> dy = 1;
                case 'R' -> dx = 1;
            }
            
            if (inGrid(x + dx, y + dy, traversedGrid) && grid[y + dy][x + dx] != '#' && ifToLoop(x, y, dir, traversedGrid)) {

            }

            if (inGrid(x + dx, y + dy, traversedGrid) && grid[y + dy][x + dx] == '#') {
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

        System.out.println(count);
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
    
    public static boolean ifToLoop(int x, int y, char dir, char[][] grid) {
        int dx = 0;
        int dy = 0;

        dir = switch (dir) {
            case 'U' -> 'R';
            case 'R' -> 'D';
            case 'D' -> 'L';
            case 'L' -> 'U';
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        };

        switch (dir) {
            case 'U' -> dy = -1;
            case 'L' -> dx = -1;
            case 'D' -> dy = 1;
            case 'R' -> dx = 1;
        }
        
        while (inGrid(x + dx, y + dy, grid)) {
            if (grid[y + dy][x + dx] == '#' && grid[y][x] != 'X') {
                return false;
            }
            if (grid[y + dy][x + dx] == '#' && grid[y][x] == 'X') {
                return true;
            }

            x += dx;
            y += dy;
        }

        return false;
    }
}
