import java.util.*;

/*
Problem: Sell Diminishing-Valued Colored Balls
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
inventory[i] is the number of balls of color i. A ball is worth the current count of its color,
and that count drops by one after each sale. Sell exactly `orders` balls for the maximum total value (mod 1e9 + 7).

Why this pattern fits:
Greedy always sells the currently most valuable ball, which simulating one by one is too slow.
Equivalent view: there is a threshold t such that every ball worth more than t is sold. Find t by bisection.

Approach:
Find the smallest t where the number of balls worth more than t, sum of max(0, inventory - t), is at most orders.
Sell those with the series (inventory + t + 1) * (inventory - t) / 2 per color; the remaining orders are sold at price t.

Worked Example:
inventory = [2, 5], orders = 4 -> t = 2: sell 5 + 4 + 3 = 12, then one more ball at price 2. Answer = 14.

Time Complexity: O(n log(maxInventory))
Space Complexity: O(1)
*/

public class P25_SellDiminishingValuedColoredBallsBinarySearchAnswer {
    public static int maxProfit(int[] inventory, int orders) {
        long mod = 1_000_000_007L;
        int low = 0;
        int high = Arrays.stream(inventory).max().getAsInt();
        while (low < high) {
            int mid = low + (high - low) / 2;
            long count = 0;
            for (int balls : inventory) {
                count += Math.max(0, balls - mid);
            }
            if (count <= orders) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        long threshold = low;
        long profit = 0;
        long sold = 0;
        for (int balls : inventory) {
            if (balls > threshold) {
                long count = balls - threshold;
                sold += count;
                profit = (profit + (balls + threshold + 1) * count / 2) % mod;
            }
        }
        profit = (profit + (orders - sold) % mod * threshold) % mod;
        return (int) profit;
    }

    public static void main(String[] args) {
        System.out.println("[2, 5], orders 4 -> " + maxProfit(new int[]{2, 5}, 4));
        System.out.println("[3, 5], orders 6 -> " + maxProfit(new int[]{3, 5}, 6));
        System.out.println("[1000000000], orders 1000000000 -> " + maxProfit(new int[]{1000000000}, 1000000000));
    }
}
