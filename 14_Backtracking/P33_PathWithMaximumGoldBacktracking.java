import java.util.*;

/*
Problem: Path with Maximum Gold
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
In a gold mine grid of size m x n, each cell contains an integer representing the amount of gold in that cell (0 if empty).
    Return the maximum amount of gold you can collect under these conditions:
    - Every time you are located in a cell, you collect all the gold in that cell.
    - From your position, you can walk one step left, right, up, or down.
    - You cannot visit the same cell more than once, and you can never visit a cell with 0 gold.
    - You can start and stop collecting gold from any cell that has some gold.

Why this pattern fits:
The best path depends on which cells have already been used, so different paths through the same cell are not interchangeable.
    That rules out memoizing by cell alone. The grid is small, so every simple path is explored with
    the classic mark, recurse, unmark routine.

Approach:
Try every gold cell as a starting point.
    DFS from a cell: remember its gold, set the cell to 0 to mark it as visited,
    take the best result among the four neighbors, then restore the cell's gold.
    The value of a path from this cell is its gold plus the best continuation.

Worked Example:
Example:
    grid = [[0, 6, 0],
            [5, 8, 7],
            [0, 9, 0]]
    The best path is 9 -> 8 -> 7, collecting 24.

Time Complexity: O(g * 3^g) worst case, where g is the number of gold cells (at most 25 in the original constraints)
Space Complexity: O(g) recursion stack
*/

public class P33_PathWithMaximumGoldBacktracking {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int getMaximumGold(int[][] grid) {
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                best = Math.max(best, collect(grid, r, c));
            }
        }
        return best;
    }

    private static int collect(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        int gold = grid[r][c];
        grid[r][c] = 0;

        int bestNext = 0;
        for (int[] direction : DIRECTIONS) {
            bestNext = Math.max(bestNext, collect(grid, r + direction[0], c + direction[1]));
        }

        grid[r][c] = gold;
        return gold + bestNext;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumGold(new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}}));
        System.out.println(getMaximumGold(new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}}));
    }
}
