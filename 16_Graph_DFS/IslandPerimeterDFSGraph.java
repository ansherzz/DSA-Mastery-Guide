import java.util.*;

/*
Problem: Island Perimeter
Pattern: 16. Graph DFS
Difficulty: Easy

Interview Prompt:
You are given a grid where 1 represents land and 0 represents water.
    The grid contains exactly one island (one group of 4-directionally connected land cells), and it has no lakes.
    Each cell is a square with side length 1. Return the perimeter of the island.

Why this pattern fits:
A grid is a graph in disguise: cells are nodes and neighboring cells are connected.
    Walking the island with DFS lets every land cell report how many of its sides face water or the grid border,
    and those sides are exactly the perimeter.

Approach:
Find any land cell and start a DFS from it.
    The DFS returns 1 when it steps off the grid or into water, because that step crossed a perimeter edge.
    It returns 0 when it reaches land that was already visited.
    For an unvisited land cell, mark it and return the sum of the results from its four neighbors.

Worked Example:
Example:
    grid = 0 1 0 0
           1 1 1 0
           0 1 0 0
           1 1 0 0
    The island has 7 cells with 28 sides in total. 6 pairs of cells share a side, hiding 12 sides.
    Perimeter = 28 - 12 = 16.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class IslandPerimeterDFSGraph {
    public static int islandPerimeter(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    return dfs(grid, visited, r, c);
                }
            }
        }
        return 0;
    }

    private static int dfs(int[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 1;
        }
        if (visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;
        return dfs(grid, visited, r + 1, c)
            + dfs(grid, visited, r - 1, c)
            + dfs(grid, visited, r, c + 1)
            + dfs(grid, visited, r, c - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0}
        };
        System.out.println("Perimeter: " + islandPerimeter(grid));
        System.out.println("Perimeter: " + islandPerimeter(new int[][]{{1}}));
        System.out.println("Perimeter: " + islandPerimeter(new int[][]{{1, 0}}));
    }
}
