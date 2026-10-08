import java.util.*;

/*
Problem: Perfect Squares
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given n, return the least number of perfect square numbers (1, 4, 9, 16, ...) that sum to n.

Why this pattern fits:
Each square may be used any number of times and we minimize the count: the unbounded knapsack / coin change recurrence.

Approach:
dp[0] = 0. For each amount i from 1 to n: dp[i] = 1 + min(dp[i - s * s]) over all squares s * s <= i.

Worked Example:
n = 12 -> 4 + 4 + 4 uses three squares; no pair of squares sums to 12. Answer = 3.

Time Complexity: O(n * sqrt(n))
Space Complexity: O(n)
*/

public class P11_PerfectSquaresDP {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int amount = 1; amount <= n; amount++) {
            dp[amount] = amount;
            for (int root = 1; root * root <= amount; root++) {
                dp[amount] = Math.min(dp[amount], dp[amount - root * root] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        for (int n : new int[]{12, 13, 1}) {
            System.out.println("n = " + n + " -> " + numSquares(n));
        }
    }
}
