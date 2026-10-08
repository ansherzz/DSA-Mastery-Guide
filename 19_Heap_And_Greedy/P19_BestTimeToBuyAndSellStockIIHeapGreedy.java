import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock II
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given an integer array prices where prices[i] is the price of a given stock on the i-th day.
    On each day, you may decide to buy and/or sell the stock. You can hold at most one share at any time,
    but you can buy it and then immediately sell it on the same day.
    Find and return the maximum profit you can achieve.

Why this pattern fits:
With unlimited transactions, the profit of any buy-low, sell-high trade equals the sum of the daily gains inside it.
    So the best plan is to collect every single upward day-to-day move and skip every downward one.
    No lookahead is needed, which is what makes it greedy.

Approach:
Scan the prices once. Whenever today's price is higher than yesterday's, add the difference to the profit.
    This is equivalent to buying at every local valley and selling at every local peak.

Worked Example:
Example:
    prices = [7, 1, 5, 3, 6, 4]
    Gains: 1 -> 5 gives 4, and 3 -> 6 gives 3. Total = 7.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P19_BestTimeToBuyAndSellStockIIHeapGreedy {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int day = 1; day < prices.length; day++) {
            if (prices[day] > prices[day - 1]) {
                profit += prices[day] - prices[day - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
