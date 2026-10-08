import java.util.*;

/*
Problem: Unique Paths III
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
You are given an m x n integer grid where each cell is one of:
    1 for the starting square (exactly one), 2 for the ending square (exactly one),
    0 for empty squares we can walk over, and -1 for obstacles that we cannot walk over.
    Return the number of 4-directional walks from the starting square to the ending square
    that walk over every non-obstacle square exactly once.

Why this pattern fits:
The requirement to visit every free square exactly once is a Hamiltonian path condition.
    There is no polynomial shortcut, but the grid is tiny (at most 20 cells), so exhaustive search with undo is expected.

Approach:
Count the squares that must be visited (every non-obstacle cell, including start and end).
    DFS from the start with the number of squares still to visit.
    On arriving at the end square, the walk is valid only if it is the last remaining square.
    Otherwise mark the current cell as an obstacle, recurse in four directions with remaining - 1, and restore the cell.

Worked Example:
Example:
    grid = [[1, 0, 0, 0],
            [0, 0, 0, 0],
            [0, 0, 2, -1]]
    There are exactly 2 walks that cover all 11 free squares and finish on the end square.

Time Complexity: O(3^(m * n)) worst case
Space Complexity: O(m * n) recursion stack
*/

public class P44_UniquePathsIIIBacktracking {
    public static int uniquePathsIII(int[][] grid) {
        int startRow = 0;
        int startCol = 0;
        int toVisit = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] != -1) {
                    toVisit++;
                }
                if (grid[r][c] == 1) {
                    startRow = r;
                    startCol = c;
                }
            }
        }
        return walk(grid, startRow, startCol, toVisit);
    }

    private static int walk(int[][] grid, int r, int c, int remaining) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == -1) {
            return 0;
        }
        if (grid[r][c] == 2) {
            return remaining == 1 ? 1 : 0;
        }

        int saved = grid[r][c];
        grid[r][c] = -1;
        int paths = walk(grid, r + 1, c, remaining - 1)
            + walk(grid, r - 1, c, remaining - 1)
            + walk(grid, r, c + 1, remaining - 1)
            + walk(grid, r, c - 1, remaining - 1);
        grid[r][c] = saved;
        return paths;
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        System.out.println(uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
        System.out.println(uniquePathsIII(new int[][]{{0, 1}, {2, 0}}));
    }
}
