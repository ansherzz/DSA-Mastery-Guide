import java.util.*;

/*
Problem: Online Stock Span
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Design a class that receives a stock's price day by day and returns the span of the price for the current day.
    The span is the maximum number of consecutive days, ending today and going backward,
    for which the price was less than or equal to today's price.

    Implement next(price), which is called once per day.

Why this pattern fits:
Once a higher or equal price arrives, earlier lower prices can never limit a future span on their own.
    They can be merged into the newer price's span and discarded, which is a monotonic stack that stores spans.

Approach:
Keep a stack of (price, span) pairs with strictly decreasing prices from bottom to top.
    For a new price, start with span 1. While the top price is <= the new price, pop it and add its span.
    Push the new (price, span) pair and return the span.

Worked Example:
Example:
    Prices: 100, 80, 60, 70, 60, 75, 85
    Spans :   1,  1,  1,  2,  1,  4,  6
    For 75, the prices 60, 70, 60 before it are all <= 75, so the span is 4.

Time Complexity: amortized O(1) per call
Space Complexity: O(n)
*/

public class P17_OnlineStockSpanStack {
    static class StockSpanner {
        private final Deque<int[]> stack = new ArrayDeque<>();

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1];
            }
            stack.push(new int[]{price, span});
            return span;
        }
    }

    public static void main(String[] args) {
        StockSpanner spanner = new StockSpanner();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            spans[i] = spanner.next(prices[i]);
        }

        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Spans : " + Arrays.toString(spans));
    }
}
