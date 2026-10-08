import java.util.*;

/*
Problem: Constrained Subsequence Sum
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
Return the maximum sum of a non-empty subsequence of nums such that
every two consecutive chosen indices i < j satisfy j - i <= k.

Why this pattern fits:
Each choice depends on the best result among the previous k positions.
That is a sliding-window maximum over dp, the textbook use of a monotonic deque.

Approach:
dp[i] is the best sum of a valid subsequence ending at i.
Use the deque front (largest dp within k) only when it is positive; otherwise start fresh at i.
Maintain decreasing dp order in the deque.

Worked Example:
nums = [10, 2, -10, 5, 20], k = 2 -> take 10, 2, 5, 20 for a sum of 37.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P48_ConstrainedSubsequenceSumSlidingWindow {
    public static int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            dp[i] = nums[i] + (deque.isEmpty() ? 0 : Math.max(0, dp[deque.peekFirst()]));
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[10, 2, -10, 5, 20], k = 2 -> " + constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2));
        System.out.println("[-1, -2, -3], k = 1 -> " + constrainedSubsetSum(new int[]{-1, -2, -3}, 1));
        System.out.println("[10, -2, -10, -5, 20], k = 2 -> " + constrainedSubsetSum(new int[]{10, -2, -10, -5, 20}, 2));
    }
}
