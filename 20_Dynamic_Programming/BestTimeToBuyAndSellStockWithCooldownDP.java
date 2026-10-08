import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock with Cooldown
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You are given an array prices where prices[i] is the price of a given stock on the i-th day.
    Find the maximum profit you can achieve. You may complete as many transactions as you like with these restrictions:
    - After you sell your stock, you cannot buy stock on the next day (a one-day cooldown).
    - You may not hold more than one share at a time, so you must sell before you buy again.

Why this pattern fits:
The cooldown makes the best action today depend on what happened yesterday,
    so a single running value is not enough. Modeling each day as a small set of states with allowed transitions
    (a state machine) captures the rule exactly.

Approach:
Track three values at the end of each day:
    - holding: the best profit while owning a share.
    - sold: the best profit having sold today (tomorrow is a cooldown day).
    - resting: the best profit while owning nothing and free to buy.
    Transitions for a new price:
    - holding = max(holding, resting - price)   (keep holding, or buy from the resting state)
    - sold    = holding + price                 (sell the share held before today)
    - resting = max(resting, sold)              (stay resting, or finish yesterday's cooldown)
    All three must be computed from yesterday's values. The answer is max(sold, resting).

Worked Example:
Example:
    prices = [1, 2, 3, 0, 2]
    Buy at 1, sell at 2, cooldown, buy at 0, sell at 2. Profit = 1 + 2 = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class BestTimeToBuyAndSellStockWithCooldownDP {
    public static int maxProfit(int[] prices) {
        int holding = Integer.MIN_VALUE / 2;
        int sold = 0;
        int resting = 0;

        for (int price : prices) {
            int previousSold = sold;
            sold = holding + price;
            holding = Math.max(holding, resting - price);
            resting = Math.max(resting, previousSold);
        }
        return Math.max(sold, resting);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(maxProfit(new int[]{1}));
        System.out.println(maxProfit(new int[]{6, 1, 3, 2, 4, 7}));
    }
}
