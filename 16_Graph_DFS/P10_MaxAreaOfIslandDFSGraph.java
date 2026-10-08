import java.util.*;

/*
Problem: Max Area of Island
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given a binary grid where 1 represents land and 0 represents water.
    An island is a group of 1s connected 4-directionally. The area of an island is the number of cells in it.
    Return the maximum area of an island in the grid, or 0 if there is no island.

Why this pattern fits:
This is connected components again, but each component must report its size.
    A recursive DFS does that naturally: the area of an island seen from one cell is
    1 plus the areas reachable through each of its neighbors.

Approach:
Scan every cell. For each unvisited land cell, run a DFS that returns the size of its island.
    The DFS marks the cell as visited by setting it to 0, then returns 1 plus the results of the four neighbors.
    Keep the largest size seen.

Worked Example:
Example:
    grid = 1 1 0 0
           1 0 0 1
           0 0 1 1
           0 0 1 1
    The top-left island has area 3 and the bottom-right island has area 5. Answer = 5.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class P10_MaxAreaOfIslandDFSGraph {
    public static int maxAreaOfIsland(int[][] grid) {
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    best = Math.max(best, area(grid, r, c));
                }
            }
        }
        return best;
    }

    private static int area(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = 0;
        return 1 + area(grid, r + 1, c) + area(grid, r - 1, c) + area(grid, r, c + 1) + area(grid, r, c - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 0, 1, 1}
        };
        System.out.println("Max area: " + maxAreaOfIsland(grid));
        System.out.println("Max area: " + maxAreaOfIsland(new int[][]{{0, 0, 0}, {0, 0, 0}}));
    }
}
