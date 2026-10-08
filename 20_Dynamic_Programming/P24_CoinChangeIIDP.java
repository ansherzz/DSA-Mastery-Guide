import java.util.*;

/*
Problem: Coin Change II
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You are given coins of different denominations and a total amount.
    Return the number of combinations of coins that make up that amount.
    You have an unlimited supply of each coin. Two combinations that differ only in order count as the same.

Why this pattern fits:
This is the counting version of the unbounded knapsack.
    The subtle part is counting combinations rather than ordered sequences,
    which is controlled entirely by the order of the two loops.

Approach:
State: ways[a] is the number of combinations that make amount a using the coins processed so far. ways[0] = 1.
    Process the coins one at a time in the outer loop. For each coin, sweep the amounts upward:
    ways[a] += ways[a - coin].
    Because each coin is fully processed before the next one is introduced, every combination is built
    in one fixed coin order and is counted exactly once.

Worked Example:
Example:
    amount = 5, coins = [1, 2, 5]
    Combinations: 5, 2 + 2 + 1, 2 + 1 + 1 + 1, 1 + 1 + 1 + 1 + 1. Answer = 4.

Time Complexity: O(amount * number of coins)
Space Complexity: O(amount)
*/

public class P24_CoinChangeIIDP {
    public static int change(int amount, int[] coins) {
        int[] ways = new int[amount + 1];
        ways[0] = 1;

        for (int coin : coins) {
            for (int current = coin; current <= amount; current++) {
                ways[current] += ways[current - coin];
            }
        }
        return ways[amount];
    }

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(3, new int[]{2}));
        System.out.println(change(10, new int[]{10}));
        System.out.println(change(0, new int[]{7}));
    }
}
