import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day08 {
    public static void main(String[] args) {
        char[][] grid = FileHelper.readToArray("input/test");
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

        for (ArrayList<Position> positions : characterPositions.values()) {
            for (int i = 0; i < positions.size() - 1; i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    createAntiNode(positions.get(i), positions.get(j), grid);
                }
            }
        }

        int sum = 0;
        for (char[] line : grid) {
            for (char character : line) {
                if (character == '#') {
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }

    public static void createAntiNode(Position pos1, Position pos2, char[][] grid) {
        int dy = pos1.dy(pos2);
        int dx = pos1.dx(pos2);

        try {
            if (grid[pos1.y - dy][pos1.x - dx] == '.') {
                grid[pos1.y - dy][pos1.x - dx] = '#';
            }
        } catch (Exception _) {
        }

        try {
            if (grid[pos2.y + dy][pos2.x + dx] == '.') {
                grid[pos2.y + dy][pos2.x + dx] = '#';
            }
        } catch (Exception _) {
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
