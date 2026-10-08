import java.util.*;

/*
Problem: Find the Safest Path in a Grid
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
In an n x n grid, 1 marks a thief. The safeness of a path is the minimum Manhattan distance
from any cell on the path to any thief. Return the maximum safeness of a path from (0, 0) to (n - 1, n - 1).

Why this pattern fits:
If a path with safeness d exists, the same path has safeness at least d - 1.
For a fixed d, the check is plain reachability using only cells at distance >= d.

Approach:
Multi-source BFS from all thieves gives every cell's distance.
Binary search d; for each candidate run a BFS from the start over cells with distance >= d.

Worked Example:
grid = [[0, 0, 1], [0, 0, 0], [0, 0, 0]] -> the path down the left column and along the bottom row keeps distance 2.

Time Complexity: O(n^2 log n)
Space Complexity: O(n^2)
*/

public class P31_FindTheSafestPathInAGridBinarySearchAnswer {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean reachable(int[][] dist, int safeness) {
        int n = dist.length;
        if (dist[0][0] < safeness || dist[n - 1][n - 1] < safeness) {
            return false;
        }
        boolean[][] seen = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        seen[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == n - 1 && cell[1] == n - 1) {
                return true;
            }
            for (int[] d : DIRECTIONS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < n && c >= 0 && c < n && !seen[r][c] && dist[r][c] >= safeness) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static int maximumSafenessFactor(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                dist[r][c] = grid[r][c] == 1 ? 0 : -1;
                if (grid[r][c] == 1) {
                    queue.add(new int[]{r, c});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < n && c >= 0 && c < n && dist[r][c] == -1) {
                    dist[r][c] = dist[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }
        int low = 0;
        int high = 2 * n;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (reachable(dist, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("thieves at both corners -> " + maximumSafenessFactor(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 1}}));
        System.out.println("one thief at top right -> " + maximumSafenessFactor(new int[][]{{0, 0, 1}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println("4x4 with two thieves -> "
                + maximumSafenessFactor(new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}}));
    }
}
