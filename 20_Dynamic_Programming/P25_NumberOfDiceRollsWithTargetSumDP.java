import java.util.*;

/*
Problem: Number of Dice Rolls With Target Sum
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You have n dice with k faces numbered 1..k. Return the number of ways (out of k^n) to roll the dice
so that the faces sum to target, modulo 1e9 + 7.

Why this pattern fits:
The dice are rolled one after another, and the only thing that matters about earlier dice is their running sum.
So the state is (dice used, sum so far) and each state is a sum of k smaller states.

Approach:
dp[0] = 1 for sum 0. For each die build a new array: next[s] = sum of dp[s - face] for face 1..k.
The answer is dp[target] after n dice.

Worked Example:
n = 2, k = 6, target = 7 -> (1,6), (2,5), (3,4), (4,3), (5,2), (6,1): 6 ways.

Time Complexity: O(n * k * target)
Space Complexity: O(target)
*/

public class P25_NumberOfDiceRollsWithTargetSumDP {
    public static int numRollsToTarget(int n, int k, int target) {
        int mod = 1_000_000_007;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int die = 0; die < n; die++) {
            int[] next = new int[target + 1];
            for (int sum = 1; sum <= target; sum++) {
                for (int face = 1; face <= k && face <= sum; face++) {
                    next[sum] = (next[sum] + dp[sum - face]) % mod;
                }
            }
            dp = next;
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println("n = 1, k = 6, target = 3 -> " + numRollsToTarget(1, 6, 3));
        System.out.println("n = 2, k = 6, target = 7 -> " + numRollsToTarget(2, 6, 7));
        System.out.println("n = 30, k = 30, target = 500 -> " + numRollsToTarget(30, 30, 500));
    }
}
