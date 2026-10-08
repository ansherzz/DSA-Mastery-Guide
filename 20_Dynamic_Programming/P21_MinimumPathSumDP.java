import java.util.*;

/*
Problem: Minimum Path Sum
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given an m x n grid filled with non-negative numbers, find a path from the top-left cell to the bottom-right cell
    that minimizes the sum of all numbers along its path. You can only move either down or right at any point in time.

Why this pattern fits:
The number of paths is exponential, but the best path into any cell must come from the best path
    into the cell above it or the cell to its left. That optimal substructure makes each cell a small subproblem.

Approach:
State: dp[c] is the minimum path sum to reach the cell in the current row at column c.
    Transition: dp[c] = grid[r][c] + min(dp[c] (from above), dp[c - 1] (from the left)).
    The first row can only be reached from the left, and the first column only from above.
    One row of state is enough because each row depends only on the row above it.

Worked Example:
Example:
    grid = [[1, 3, 1],
            [1, 5, 1],
            [4, 2, 1]]
    The path 1 -> 3 -> 1 -> 1 -> 1 has sum 7, which is the minimum.

Time Complexity: O(m * n)
Space Complexity: O(n)
*/

public class P21_MinimumPathSumDP {
    public static int minPathSum(int[][] grid) {
        int cols = grid[0].length;
        int[] dp = new int[cols];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 && c == 0) {
                    dp[c] = grid[0][0];
                } else if (r == 0) {
                    dp[c] = dp[c - 1] + grid[r][c];
                } else if (c == 0) {
                    dp[c] = dp[c] + grid[r][c];
                } else {
                    dp[c] = Math.min(dp[c], dp[c - 1]) + grid[r][c];
                }
            }
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}
