import java.util.*;

/*
Problem: Triangle
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given a triangle array, return the minimum path sum from top to bottom.
    For each step, you may move to an adjacent number of the row below:
    if you are on index i of the current row, you may move to index i or index i + 1 of the next row.

Why this pattern fits:
A greedy choice of the smaller neighbor fails, because a small number now can lead to large numbers later.
    But the best path from a cell to the bottom depends only on the best paths from its two children,
    so the subproblems can be solved from the bottom row upward.

Approach:
State: dp[i] is the minimum path sum from position i of the current row down to the bottom.
    Start with dp equal to the last row.
    Move upward row by row: dp[i] = triangle[row][i] + min(dp[i], dp[i + 1]).
    Processing i from left to right is safe because dp[i + 1] still holds the value from the row below.
    Going bottom-up avoids any boundary checks and ends with the answer in dp[0].

Worked Example:
Example:
       2
      3 4
     6 5 7
    4 1 8 3
    Bottom-up: [4, 1, 8, 3] -> [7, 6, 10] -> [9, 10] -> [11]. The path is 2 + 3 + 5 + 1 = 11.

Time Complexity: O(n^2), where n is the number of rows
Space Complexity: O(n)
*/

public class TriangleDP {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int i = 0; i <= row; i++) {
                dp[i] = triangle.get(row).get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(minimumTotal(Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3))));
        System.out.println(minimumTotal(Arrays.asList(Arrays.asList(-10))));
    }
}
