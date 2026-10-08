import java.util.*;

/*
Problem: Swim in Rising Water
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
grid[i][j] is the elevation of a cell in an n x n grid. At time t the water depth is t,
and you can move between adjacent cells whose elevations are both at most t.
Return the least time needed to get from the top-left to the bottom-right.

Why this pattern fits:
A higher water level only opens more cells, so reachability is monotonic in t.

Approach:
Search t between grid[0][0] and the maximum elevation.
For each candidate, DFS from (0, 0) through cells with elevation <= t and see whether the last cell is reached.

Worked Example:
grid = [[0, 2], [1, 3]] -> the destination has elevation 3, so the answer is 3.

Time Complexity: O(n^2 log(maxElevation))
Space Complexity: O(n^2)
*/

public class P38_SwimInRisingWaterBinarySearchAnswer {
    private static boolean canReach(int[][] grid, int level) {
        int n = grid.length;
        boolean[][] seen = new boolean[n][n];
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        seen[0][0] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            if (cell[0] == n - 1 && cell[1] == n - 1) {
                return true;
            }
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < n && c >= 0 && c < n && !seen[r][c] && grid[r][c] <= level) {
                    seen[r][c] = true;
                    stack.push(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static int swimInWater(int[][] grid) {
        int low = grid[0][0];
        int high = 0;
        for (int[] row : grid) {
            for (int value : row) {
                high = Math.max(high, value);
            }
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canReach(grid, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[[0, 2], [1, 3]] -> " + swimInWater(new int[][]{{0, 2}, {1, 3}}));
        int[][] spiral = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        System.out.println("5x5 spiral -> " + swimInWater(spiral));
    }
}
