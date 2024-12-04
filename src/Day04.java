import java.util.ArrayList;

public class Day04 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        ArrayList<String> data = FileHelper.read("input/Day04");

        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            char[] row = data.get(i).toCharArray();
            grid[i] = row;
        }

        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sum += matchesPartOne(i, j, grid);
            }
        }

        System.out.println(sum);
    }

    public static void partTwo() {
        ArrayList<String> data = FileHelper.read("input/Day04");

        char[][] grid = new char[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            char[] row = data.get(i).toCharArray();
            grid[i] = row;
        }

        int sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sum += matchesPartTwo(i, j, grid) ? 1 : 0;
            }
        }


        System.out.println(sum);
    }

    public static int matchesPartOne(int row, int col, char[][] grid) {
        if (grid[row][col] != 'X') {
            return 0;
        }

        int maxRows = grid.length - 1;
        int maxCols = grid[0].length - 1;
        String toMatch = "XMAS";
        ArrayList<String> toComp = new ArrayList<>();


        if (row + 3 <= maxRows) {
            toComp.add("" + grid[row][col] + grid[row + 1][col] + grid[row + 2][col] + grid[row + 3][col]);
        }

        if (col + 3 <= maxCols) {
            toComp.add("" + grid[row][col] + grid[row][col + 1] + grid[row][col + 2] + grid[row][col + 3]);
        }

        if (row + 3 <= maxRows && col + 3 <= maxCols) {
            toComp.add("" + grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2] + grid[row + 3][col + 3]);
        }

        if (row + 3 <= maxRows && col - 3 >= 0) {
            toComp.add("" + grid[row][col] + grid[row + 1][col - 1] + grid[row + 2][col - 2] + grid[row + 3][col - 3]);
        }

        if (row - 3 >= 0) {
            toComp.add("" + grid[row][col] + grid[row - 1][col] + grid[row - 2][col] + grid[row - 3][col]);
        }

        if (col - 3 >= 0) {
            toComp.add("" + grid[row][col] + grid[row][col - 1] + grid[row][col - 2] + grid[row][col - 3]);
        }

        if (row - 3 >= 0 && col - 3 >= 0) {
            toComp.add("" + grid[row][col] + grid[row - 1][col - 1] + grid[row +- 2][col - 2] + grid[row - 3][col - 3]);
        }

        if (row - 3 >= 0 && col + 3 <= maxCols) {
            toComp.add("" + grid[row][col] + grid[row - 1][col + 1] + grid[row - 2][col + 2] + grid[row - 3][col + 3]);
        }

        return (int) toComp.stream().filter(toMatch::equals).count();
    }

    public static boolean matchesPartTwo(int row, int col, char[][] grid) {
        int maxRows = grid.length - 1;
        int maxCols = grid[0].length - 1;

        if (grid[row][col] != 'A' || row - 1 < 0 || col - 1 < 0 || row + 1 > maxRows | col + 1 > maxCols) {
            return false;
        }

        char[] corners = {
                grid[row - 1][col - 1], grid[row - 1][col + 1],
                grid[row + 1][col - 1], grid[row + 1][col + 1]
        };

        // ensure corners are only M and S
        for (char letter : corners) {
            if (letter != 'M' && letter != 'S') {
                return false;
            }
        }

        return corners[0] == corners[1] && corners[2] == corners[3] && corners[0] != corners[2] || // top and bottom are = and different
                corners[0] == corners[2] && corners[1] == corners[3] && corners[0] != corners[1]; // left and right are = and different
    }
}
