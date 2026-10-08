import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock IV
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
You may complete at most k transactions (buy then sell, never holding more than one share).
Return the maximum profit.

Why this pattern fits:
The two-state stock machine gains a counter: how many transactions have been used.
Each transaction j has its own "holding" and "sold" value, and buying for transaction j starts from the profit of transaction j - 1.

Approach:
buy[j] = best balance while holding the share of transaction j; sell[j] = best profit after completing j transactions.
For each price and each j: buy[j] = max(buy[j], sell[j - 1] - price); sell[j] = max(sell[j], buy[j] + price).

Worked Example:
k = 2, prices = [3, 2, 6, 5, 0, 3] -> buy 2 sell 6, buy 0 sell 3. Profit = 7.

Time Complexity: O(n * k)
Space Complexity: O(k)
*/

public class P44_BestTimeToBuyAndSellStockIVDP {
    public static int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 2);
        for (int price : prices) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - price);
                sell[j] = Math.max(sell[j], buy[j] + price);
            }
        }
        return sell[k];
    }

    public static void main(String[] args) {
        System.out.println("k = 2, [2, 4, 1] -> " + maxProfit(2, new int[]{2, 4, 1}));
        System.out.println("k = 2, [3, 2, 6, 5, 0, 3] -> " + maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }
}
