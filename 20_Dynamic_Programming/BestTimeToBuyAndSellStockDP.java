import java.util.*;

/*
Problem: Best Time to Buy and Sell Stock
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
You are given an array prices where prices[i] is the price of a stock on day i.
    You may buy on one day and sell on a later day, completing at most one transaction.
    Return the maximum profit you can achieve, or 0 if no profit is possible.

    The brute-force version is in folder 01. Solve it here in a single pass.

Why this pattern fits:
The best profit when selling on day i is prices[i] minus the lowest price on any earlier day.
    The "lowest price so far" is a running state carried from one day to the next,
    so the answer for each day is built from the answer state of the day before.

Approach:
State: minPrice is the cheapest price seen up to the current day, and best is the best profit so far.
    For each day, first consider selling today (price - minPrice) and update best,
    then update minPrice with today's price.

Worked Example:
Example:
    prices = [7, 1, 5, 3, 6, 4]
    The lowest price is 1 on day 1, and the highest later price is 6 on day 4. Profit = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class BestTimeToBuyAndSellStockDP {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                best = Math.max(best, price - minPrice);
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
