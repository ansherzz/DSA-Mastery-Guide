import java.util.*;

/*
Problem: Unique Paths
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
A robot is in the top-left corner of an m x n grid and wants to reach the bottom-right corner.
    It can only move one cell down or one cell right at a time.
    Return the number of different paths the robot can take.

Why this pattern fits:
Every path into a cell arrives either from the cell above or from the cell to the left.
    So the number of paths to a cell is the sum of the counts for those two cells,
    which is a recurrence over a grid of subproblems.

Approach:
State: dp[c] is the number of paths to the cell in the current row at column c.
    Base: the first row has exactly one path to each cell.
    Transition: when moving to the next row, dp[c] (still holding the value from above) plus dp[c - 1]
    (already updated, the value from the left) gives the new dp[c]. One row of memory is enough.

Worked Example:
Example:
    m = 3, n = 3
    Path counts:
        1 1 1
        1 2 3
        1 3 6
    Answer = 6.

Time Complexity: O(m * n)
Space Complexity: O(n)
*/

public class P12_UniquePathsDP {
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[col] += dp[col - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("3 x 7 -> " + uniquePaths(3, 7));
        System.out.println("3 x 2 -> " + uniquePaths(3, 2));
        System.out.println("3 x 3 -> " + uniquePaths(3, 3));
    }
}
