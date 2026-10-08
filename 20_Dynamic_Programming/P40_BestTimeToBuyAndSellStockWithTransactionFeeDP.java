import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock with Transaction Fee
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You may complete as many buy/sell transactions as you like (holding at most one share),
paying a fee for every transaction. Return the maximum profit.

Why this pattern fits:
Each day you are in one of two states, and each state's best value follows from yesterday's two values.
Writing the transitions between states is the whole solution.

Approach:
cash = best profit with no share, hold = best profit while holding a share.
cash = max(cash, hold + price - fee); hold = max(hold, cash - price).

Worked Example:
prices = [1, 3, 2, 8, 4, 9], fee = 2 -> buy at 1, sell at 8 (profit 5), buy at 4, sell at 9 (profit 3). Total = 8.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P40_BestTimeToBuyAndSellStockWithTransactionFeeDP {
    public static int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int previousCash = cash;
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, previousCash - prices[i]);
        }
        return cash;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 2, 8, 4, 9], fee 2 -> " + maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println("[1, 3, 7, 5, 10, 3], fee 3 -> " + maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }
}
