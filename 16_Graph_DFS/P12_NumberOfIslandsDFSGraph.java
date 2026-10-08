import java.util.*;

/*
Problem: Number of Islands using DFS
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given an m x n grid of '1's (land) and '0's (water), return the number of islands.
    An island is a group of land cells connected horizontally or vertically, surrounded by water.

    The BFS version is in folder 11. Solve it here with depth-first search.

Why this pattern fits:
Counting islands is counting connected components in a grid graph.
    Each time an unvisited land cell is found, it belongs to a new component,
    and one DFS from it removes the entire component from further consideration.

Approach:
Scan every cell. When a '1' is found, increment the island count and run DFS from it.
    The DFS "sinks" the island by overwriting each visited land cell with '0',
    then recurses into the four neighbors. Sinking doubles as the visited marker.

Worked Example:
Example:
    grid = 1 1 0 0 0
           1 1 0 0 0
           0 0 1 0 0
           0 0 0 1 1
    There are three separate groups of land. Answer = 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class P12_NumberOfIslandsDFSGraph {
    public static int numIslands(char[][] grid) {
        int islands = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    sink(grid, r, c);
                }
            }
        }
        return islands;
    }

    private static void sink(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0';
        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

    private static char[][] parse(String... rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = rows[i].toCharArray();
        }
        return grid;
    }

    public static void main(String[] args) {
        System.out.println("Islands: " + numIslands(parse("11110", "11010", "11000", "00000")));
        System.out.println("Islands: " + numIslands(parse("11000", "11000", "00100", "00011")));
    }
}
