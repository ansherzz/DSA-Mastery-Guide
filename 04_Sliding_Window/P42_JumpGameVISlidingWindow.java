import java.util.*;

/*
Problem: Jump Game VI
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Starting at index 0, you may jump forward at most k steps at a time until you reach the last index.
Your score is the sum of nums at every visited index. Return the maximum score.

Why this pattern fits:
The recurrence needs the maximum of a sliding window of the last k dp values.
A monotonic deque turns the O(n * k) DP into O(n).

Approach:
Keep a deque of indices with decreasing dp values.
Drop the front when it is more than k behind, compute dp[i] from the front,
then pop smaller-or-equal values from the back before pushing i.

Worked Example:
nums = [1, -1, -2, 4, -7, 3], k = 2 -> visit 1, -1, 4, 3 for a score of 7.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P42_JumpGameVISlidingWindow {
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        dp[0] = nums[0];
        deque.addLast(0);
        for (int i = 1; i < n; i++) {
            if (deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            dp[i] = nums[i] + dp[deque.peekFirst()];
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("[1, -1, -2, 4, -7, 3], k = 2 -> " + maxResult(new int[]{1, -1, -2, 4, -7, 3}, 2));
        System.out.println("[10, -5, -2, 4, 0, 3], k = 3 -> " + maxResult(new int[]{10, -5, -2, 4, 0, 3}, 3));
        System.out.println("[1, -5, -20, 4, -1, 3, -6, -3], k = 2 -> " + maxResult(new int[]{1, -5, -20, 4, -1, 3, -6, -3}, 2));
    }
}
