import java.util.*;

/*
Problem: Final Prices With a Special Discount in a Shop
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
prices[i] is the price of the i-th item. Buying item i gives a discount equal to prices[j],
where j is the smallest index with j > i and prices[j] <= prices[i]. If no such j exists there is no discount.
Return the final price of each item.

Why this pattern fits:
"First later element that is smaller or equal" is the next-smaller-element pattern.
A stack of indices still waiting for their discount resolves each index exactly once.

Approach:
Scan left to right. While the current price is <= the price at the index on top of the stack,
pop that index and subtract the current price from it. Then push the current index.

Worked Example:
prices = [8, 4, 6, 2, 3] -> 8 gets 4 off, 4 gets 2 off, 6 gets 2 off. Answer = [4, 2, 4, 2, 3].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P04_FinalPricesWithASpecialDiscountStack {
    public static int[] finalPrices(int[] prices) {
        int[] result = prices.clone();
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!waiting.isEmpty() && prices[waiting.peek()] >= prices[i]) {
                result[waiting.pop()] -= prices[i];
            }
            waiting.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        for (int[] prices : new int[][]{{8, 4, 6, 2, 3}, {1, 2, 3, 4, 5}, {10, 1, 1, 6}}) {
            System.out.println(Arrays.toString(prices) + " -> " + Arrays.toString(finalPrices(prices)));
        }
    }
}
