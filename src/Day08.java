import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day08 {
    public static void main(String[] args) {
        char[][] grid = FileHelper.readTo2DArray("input/Day08");
        HashMap<Character, ArrayList<Position>> characterPositions = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char character = grid[i][j];
                if (character != '.') {
                    characterPositions.putIfAbsent(character, new ArrayList<>());
                    characterPositions.get(character).add(new Position(i, j));
                }
            }
        }

        int sum = 0;

        for (ArrayList<Position> positions : characterPositions.values()) {
            for (int i = 0; i < positions.size() - 1; i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    createAntiNode(positions.get(i), positions.get(j), grid);
                }
            }
        }

        for (char[] line : grid) {
            System.out.println(Arrays.toString(line));
            for (char character : line) {
                if (character == '#') {
                    sum++;
                }
            }
        }

        System.out.println("Part 1 " + sum);

        int sum2 = 0;

        char[][] grid2 = FileHelper.readTo2DArray("input/Day08");
        for (ArrayList<Position> positions : characterPositions.values()) {
            for (int i = 0; i < positions.size() - 1; i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    createAntiNodeForever(positions.get(i), positions.get(j), grid2);
                }
            }
        }

        for (char[] line : grid2) {
            System.out.println(Arrays.toString(line));
            for (char character : line) {
                if (character == '#') {
                    sum2++;
                }
            }
        }

        System.out.println("Part 2 " + sum2);
    }

    public static void createAntiNode(Position pos1, Position pos2, char[][] grid) {
        int dy = pos1.dy(pos2);
        int dx = pos1.dx(pos2);

        try {
                grid[pos1.y - dy][pos1.x - dx] = '#';
        } catch (Exception _) {}

        try {
                grid[pos2.y + dy][pos2.x + dx] = '#';
        } catch (Exception _) {}
    }

    public static void createAntiNodeForever(Position pos1, Position pos2, char[][] grid) {
        int dy = pos1.dy(pos2);
        int dx = pos1.dx(pos2);

        int upY = pos1.y;
        int upX = pos1.x;

        int downY = pos2.y;
        int downX = pos2.x;

        int yBound = grid.length - 1;
        int xBound = grid[0].length - 1;

        while (0 <= upY && upY <= yBound && 0 <= upX && upX <= xBound) {
            grid[upY][upX] = '#';

            upY -= dy;
            upX -= dx;
        }

        while (0 <= downY && downY <= yBound && 0 <= downX && downX <= xBound) {
            grid[downY][downX] = '#';

            downY += dy;
            downX += dx;
        }
    }

    public static class Position {
        public int y;
        public int x;
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int dy(Position other) {
            return other.y - this.y;
        }
        public int dx(Position other) {
            return other.x - this.x;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
