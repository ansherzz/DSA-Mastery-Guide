import java.util.*;

/*
Problem: Burst Balloons
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
You are given n balloons in a row, each painted with a number.
    Bursting balloon i earns nums[i - 1] * nums[i] * nums[i + 1] coins, using its current neighbors.
    After a balloon bursts, its neighbors become adjacent. A missing neighbor at either end counts as 1.

    Return the maximum number of coins you can collect by bursting all the balloons.

Why this pattern fits:
Choosing which balloon to burst first changes the neighbors of everything else, so subproblems are not independent.
    The trick is to think backward: choose which balloon in a range is burst last.
    At that moment its neighbors are the fixed boundaries of the range, and the two sides
    have already been cleared independently. This is interval DP.

Approach:
Pad the array with 1 at both ends.
    State: dp[left][right] is the best score from bursting every balloon strictly between left and right.
    Transition: for each k between them chosen as the last balloon to burst,
    score = dp[left][k] + padded[left] * padded[k] * padded[right] + dp[k][right].
    Fill the table by increasing interval length. The answer is dp[0][n + 1].

Worked Example:
Example:
    nums = [3, 1, 5, 8]
    Burst 1 (3 * 1 * 5 = 15), then 5 (3 * 5 * 8 = 120), then 3 (1 * 3 * 8 = 24), then 8 (1 * 8 * 1 = 8).
    Total = 167.

Time Complexity: O(n^3)
Space Complexity: O(n^2)
*/

public class BurstBalloonsDP {
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] padded = new int[n + 2];
        padded[0] = 1;
        padded[n + 1] = 1;
        System.arraycopy(nums, 0, padded, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int length = 2; length <= n + 1; length++) {
            for (int left = 0; left + length <= n + 1; left++) {
                int right = left + length;
                for (int last = left + 1; last < right; last++) {
                    int coins = dp[left][last] + padded[left] * padded[last] * padded[right] + dp[last][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        return dp[0][n + 1];
    }

    private static void runDemo(int[] nums) {
        System.out.println(Arrays.toString(nums) + " -> " + maxCoins(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 1, 5, 8});
        runDemo(new int[]{1, 5});
        runDemo(new int[]{7});
    }
}
