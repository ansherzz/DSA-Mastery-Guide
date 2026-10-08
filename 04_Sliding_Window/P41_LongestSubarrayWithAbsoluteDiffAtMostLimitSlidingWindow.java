import java.util.*;

/*
Problem: Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Return the size of the longest subarray such that the absolute difference
between any two of its elements is at most limit.

Why this pattern fits:
The constraint is max - min <= limit. A sliding window needs the max and min of a changing range,
which two monotonic deques provide in amortized O(1).

Approach:
Keep a decreasing deque for the maximum and an increasing deque for the minimum.
After adding nums[right], while max - min > limit advance left, popping a deque front when it equals nums[left].

Worked Example:
nums = [10, 1, 2, 4, 7, 2], limit = 5 -> [2, 4, 7, 2] has max 7 and min 2. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P41_LongestSubarrayWithAbsoluteDiffAtMostLimitSlidingWindow {
    public static int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int best = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }
            maxDeque.addLast(nums[right]);
            minDeque.addLast(nums[right]);
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[8, 2, 4, 7], limit = 4 -> " + longestSubarray(new int[]{8, 2, 4, 7}, 4));
        System.out.println("[10, 1, 2, 4, 7, 2], limit = 5 -> " + longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
        System.out.println("[4, 2, 2, 2, 4, 4, 2, 2], limit = 0 -> " + longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }
}
