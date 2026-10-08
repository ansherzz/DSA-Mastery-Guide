import java.util.*;

/*
Problem: Number of Closed Islands
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given a 2D grid consisting of 0s (land) and 1s (water), an island is a maximal 4-directionally connected group of 0s.
    A closed island is an island totally surrounded by water on the left, top, right, and bottom,
    which means it does not touch the border of the grid. Return the number of closed islands.

Why this pattern fits:
An island fails to be closed only if it reaches the border.
    Instead of testing every island, flood away everything connected to the border first.
    Whatever land remains is closed by construction, and counting it is the standard island count.

Approach:
Phase 1: run DFS from every land cell on the border, turning the visited land into water.
    Phase 2: scan the interior. Every remaining land cell starts a new closed island;
    count it and flood it so it is not counted again.

Worked Example:
Example:
    grid = [[0, 0, 1, 0, 0],
            [0, 1, 0, 1, 0],
            [0, 1, 1, 1, 0]]
    The land along the border is all connected to the edge. Only the single 0 in the middle is enclosed. Answer = 1.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class P30_NumberOfClosedIslandsDFSGraph {
    public static int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            sink(grid, r, 0);
            sink(grid, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            sink(grid, 0, c);
            sink(grid, rows - 1, c);
        }

        int islands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    islands++;
                    sink(grid, r, c);
                }
            }
        }
        return islands;
    }

    private static void sink(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 0) {
            return;
        }

        grid[r][c] = 1;
        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

    public static void main(String[] args) {
        int[][] first = {
            {1, 1, 1, 1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0}};
        System.out.println(closedIsland(first));

        int[][] second = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}};
        System.out.println(closedIsland(second));
    }
}
