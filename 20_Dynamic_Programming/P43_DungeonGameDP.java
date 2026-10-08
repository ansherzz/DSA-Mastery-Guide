import java.util.*;

/*
Problem: Dungeon Game
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
A knight travels from the top-left to the bottom-right of a grid, moving only right or down.
Each room adds or removes health, and his health must stay at least 1 at all times.
Return the minimum initial health required.

Why this pattern fits:
Going forward, a path's value depends on both its sum and its lowest point, which do not combine into one number.
Going backward, "health needed to survive from here to the end" is a single well-defined value per cell.

Approach:
need[r][c] = max(1, min(need[r + 1][c], need[r][c + 1]) - dungeon[r][c]),
with a sentinel of 1 just beyond the princess's cell.

Worked Example:
dungeon = [[-2, -3, 3], [-5, -10, 1], [10, 30, -5]] -> the route right, right, down, down needs 7 health.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P43_DungeonGameDP {
    public static int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        int[][] need = new int[rows + 1][cols + 1];
        for (int[] row : need) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        need[rows][cols - 1] = 1;
        need[rows - 1][cols] = 1;
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                int next = Math.min(need[r + 1][c], need[r][c + 1]);
                need[r][c] = Math.max(1, next - dungeon[r][c]);
            }
        }
        return need[0][0];
    }

    public static void main(String[] args) {
        System.out.println("3x3 dungeon -> " + calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
        System.out.println("[[0]] -> " + calculateMinimumHP(new int[][]{{0}}));
    }
}
