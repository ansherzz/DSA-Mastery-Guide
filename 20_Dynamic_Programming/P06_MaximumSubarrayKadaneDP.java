import java.util.*;

/*
Problem: Maximum Subarray (Kadane's Algorithm)
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
Given an integer array, find the contiguous subarray with the largest sum and return that sum.

Why this pattern fits:
Let dp[i] be the largest sum of a subarray that ends exactly at index i.
It depends only on dp[i - 1], so the whole table collapses into one running variable.

Approach:
current = max(nums[i], current + nums[i]); best = max(best, current).
A negative running sum is never worth carrying forward, which is what the max expresses.

Worked Example:
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4] -> the running value restarts at 4 and grows to 6 with [4, -1, 2, 1].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P06_MaximumSubarrayKadaneDP {
    public static int maxSubArray(int[] nums) {
        int current = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[-2, 1, -3, 4, -1, 2, 1, -5, 4] -> " + maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("[1] -> " + maxSubArray(new int[]{1}));
        System.out.println("[5, 4, -1, 7, 8] -> " + maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }
}
