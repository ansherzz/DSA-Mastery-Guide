import java.util.*;

/*
Problem: Maximum Subarray - Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an integer array, return the largest possible sum of any non-empty contiguous subarray.

    This file teaches the brute-force version first: examine every start index and extend every possible end index.

Why this pattern fits:
Maximum subarray is often taught with Kadane's algorithm, but interview confidence improves when you can first
    describe and implement the complete search over all subarrays.

Approach:
Choose every start index. As you extend the end index, keep a running sum and update the best answer.
    This still checks every contiguous subarray, but avoids recomputing the entire sum from scratch.

Worked Example:
Example:
    nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    The best subarray is [4, -1, 2, 1] with sum 6.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class MaximumSubarrayBruteForce {
    public static int maxSubArray(int[] nums) {
        int best = Integer.MIN_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                best = Math.max(best, sum);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array : " + Arrays.toString(nums));
        System.out.println("Best  : " + maxSubArray(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        runDemo(new int[]{1});
    }
}
