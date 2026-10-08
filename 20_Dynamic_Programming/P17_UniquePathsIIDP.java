import java.util.*;

/*
Problem: Unique Paths II
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
A robot moves only right or down from the top-left to the bottom-right of a grid.
Cells marked 1 are obstacles. Return the number of distinct paths.

Why this pattern fits:
The number of ways to reach a cell is the sum of the ways to reach the cell above and the cell to the left.
Obstacles simply force that number to zero, and the recurrence handles the rest.

Approach:
Use a one-row dp array. For each cell: if it is an obstacle set dp[c] = 0, otherwise dp[c] += dp[c - 1].

Worked Example:
A 3 x 3 grid with an obstacle in the center has 2 paths: around the top-right or around the bottom-left.

Time Complexity: O(rows * cols)
Space Complexity: O(cols)
*/

public class P17_UniquePathsIIDP {
    public static int uniquePathsWithObstacles(int[][] grid) {
        int cols = grid[0].length;
        int[] dp = new int[cols];
        dp[0] = 1;
        for (int[] row : grid) {
            for (int c = 0; c < cols; c++) {
                if (row[c] == 1) {
                    dp[c] = 0;
                } else if (c > 0) {
                    dp[c] += dp[c - 1];
                }
            }
        }
        return dp[cols - 1];
    }

    public static void main(String[] args) {
        System.out.println("3x3 with center blocked -> " + uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println("[[0, 1], [0, 0]] -> " + uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
        System.out.println("[[1]] -> " + uniquePathsWithObstacles(new int[][]{{1}}));
    }
}
