import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock - Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
You are given daily stock prices. Choose one day to buy and a later day to sell.
    Return the maximum profit possible. If no profit is possible, return 0.

    This version intentionally uses the brute-force comparison of every buy day with every later sell day.

Why this pattern fits:
The brute-force mindset is valuable because it makes the constraint 'buy before sell' explicit.
    Once you understand the baseline, it becomes easier to appreciate the linear-time optimization.

Approach:
For each buy day i, test every sell day j > i.
    Compute prices[j] - prices[i] and keep the maximum profit seen so far.

Worked Example:
Example:
    prices = [7, 1, 5, 3, 6, 4]
    Best choice: buy at 1 and sell at 6, profit = 5.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P02_BestTimeToBuySellStockBruteForce {
    public static int maxProfit(int[] prices) {
        int best = 0;
        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = buy + 1; sell < prices.length; sell++) {
                best = Math.max(best, prices[sell] - prices[buy]);
            }
        }
        return best;
    }

    private static void runDemo(int[] prices) {
        System.out.println("Prices : " + Arrays.toString(prices));
        System.out.println("Profit : " + maxProfit(prices));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{7, 1, 5, 3, 6, 4});
        runDemo(new int[]{7, 6, 4, 3, 1});
    }
}
