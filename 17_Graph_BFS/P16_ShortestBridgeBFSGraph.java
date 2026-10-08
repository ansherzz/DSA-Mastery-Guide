import java.util.*;

/*
Problem: Shortest Bridge
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given an n x n binary grid where 1 is land and 0 is water.
    The grid contains exactly two islands (groups of 4-directionally connected land).
    You may flip 0s to 1s to connect the two islands into one.
    Return the smallest number of 0s that must be flipped.

Why this pattern fits:
The answer is the shortest water distance between the two islands.
    Shortest distance on a grid with unit steps is BFS, and starting the BFS from every cell
    of one island at once (multi-source BFS) measures the distance from the island as a whole.

Approach:
Find any land cell and flood-fill its island, marking the cells as visited and adding all of them to a queue.
    Then expand outward from the whole island layer by layer.
    The first time the expansion touches an unvisited land cell, that cell belongs to the second island,
    and the number of completed layers is the number of water cells to flip.

Worked Example:
Example:
    grid = 0 1 0
           0 0 0
           0 0 1
    The islands are at (0, 1) and (2, 2). A bridge needs two water cells, for example (1, 1) and (1, 2).
    Answer = 2.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P16_ShortestBridgeBFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();

        boolean found = false;
        for (int r = 0; r < n && !found; r++) {
            for (int c = 0; c < n && !found; c++) {
                if (grid[r][c] == 1) {
                    collectIsland(grid, r, c, visited, queue);
                    found = true;
                }
            }
        }

        int flips = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int row = cell[0] + direction[0];
                    int col = cell[1] + direction[1];
                    if (row < 0 || col < 0 || row >= n || col >= n || visited[row][col]) {
                        continue;
                    }
                    if (grid[row][col] == 1) {
                        return flips;
                    }
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col});
                }
            }
            flips++;
        }
        return -1;
    }

    private static void collectIsland(int[][] grid, int r, int c, boolean[][] visited, Queue<int[]> queue) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid.length || visited[r][c] || grid[r][c] == 0) {
            return;
        }
        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        for (int[] direction : DIRECTIONS) {
            collectIsland(grid, r + direction[0], c + direction[1], visited, queue);
        }
    }

    public static void main(String[] args) {
        System.out.println(shortestBridge(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
        System.out.println(shortestBridge(new int[][]{
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}}));
    }
}
