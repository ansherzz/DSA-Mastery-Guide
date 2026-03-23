import java.util.*;

/*
Problem: Coin Change
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given coin denominations and a target amount, return the minimum number of coins needed to make that amount.
    Return -1 if it cannot be formed.

    This is a minimum-cost unbounded knapsack style DP.

Why this pattern fits:
Each amount can be built from smaller amounts.
    The state transition is:
    dp[amount] = min(dp[amount], dp[amount - coin] + 1).

Approach:
Initialize dp[0] = 0 and all other states to a large sentinel.
    For each amount from 1 to target, try all coins that can contribute to it.

Worked Example:
Example:
    coins = [1,2,5], amount = 11
    The answer is 3 because 11 = 5 + 5 + 1.

Time Complexity: O(amount * number of coins)
Space Complexity: O(amount)
*/

public class CoinChangeDP {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int current = 1; current <= amount; current++) {
            for (int coin : coins) {
                if (coin <= current) {
                    dp[current] = Math.min(dp[current], dp[current - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }
}
