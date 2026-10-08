import java.util.*;

/*
Problem: Maximum Number of Robots Within Budget
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Running k consecutive robots costs max(chargeTimes) + k * sum(runningCosts) over those robots.
Return the maximum number of consecutive robots that can run without exceeding the budget.

Why this pattern fits:
If k consecutive robots fit the budget, any k - 1 of them in a row fit as well.
For a fixed k, every window is evaluated with a running sum and a monotonic deque for the maximum.

Approach:
Search k in [0, n]. For a candidate, slide a window of size k maintaining the sum of running costs
and a decreasing deque of charge times; the size is feasible if any window costs at most the budget.

Worked Example:
chargeTimes = [3, 6, 1, 3, 4], runningCosts = [2, 1, 3, 4, 5], budget = 25
The first three robots cost 6 + 3 * 6 = 24. No window of four fits. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P43_MaximumNumberOfRobotsWithinBudgetBinarySearchAnswer {
    private static boolean fits(int[] chargeTimes, int[] runningCosts, long budget, int k) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        long sum = 0;
        for (int i = 0; i < chargeTimes.length; i++) {
            sum += runningCosts[i];
            while (!maxDeque.isEmpty() && chargeTimes[maxDeque.peekLast()] <= chargeTimes[i]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(i);
            if (i >= k) {
                sum -= runningCosts[i - k];
                if (maxDeque.peekFirst() == i - k) {
                    maxDeque.pollFirst();
                }
            }
            if (i >= k - 1 && chargeTimes[maxDeque.peekFirst()] + k * sum <= budget) {
                return true;
            }
        }
        return false;
    }

    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int low = 0;
        int high = chargeTimes.length;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (fits(chargeTimes, runningCosts, budget, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("budget 25 -> " + maximumRobots(new int[]{3, 6, 1, 3, 4}, new int[]{2, 1, 3, 4, 5}, 25));
        System.out.println("budget 19 -> " + maximumRobots(new int[]{11, 12, 19}, new int[]{10, 8, 7}, 19));
    }
}
