import java.util.*;

/*
Problem: Minimum Size Subarray Sum
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a positive integer array and a target sum, return the minimal length of a contiguous subarray
    whose sum is at least the target. If no such subarray exists, return 0.

    Because all numbers are positive, the window sum changes predictably as pointers move.

Why this pattern fits:
Positive numbers are the giveaway. Once the window sum is large enough, shrinking from the left
    can only decrease the sum, so the shortest valid window can be found efficiently.

Approach:
Expand the right pointer and maintain the current sum.
    While the sum is at least the target, update the answer and shrink from the left.

Worked Example:
Example:
    target = 7, nums = [2, 3, 1, 2, 4, 3]
    The shortest valid window is [4, 3], so the answer is 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P06_MinimumSizeSubarraySumSlidingWindow {
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left++];
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }

    private static void runDemo(int target, int[] nums) {
        System.out.println("Target: " + target);
        System.out.println("Array : " + Arrays.toString(nums));
        System.out.println("Best  : " + minSubArrayLen(target, nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(7, new int[]{2, 3, 1, 2, 4, 3});
        runDemo(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1});
    }
}
