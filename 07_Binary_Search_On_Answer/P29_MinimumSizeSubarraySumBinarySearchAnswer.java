import java.util.*;

/*
Problem: Minimum Size Subarray Sum (Binary Search on Length)
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given positive integers nums and a target, return the minimal length of a subarray
whose sum is at least the target, or 0 if no such subarray exists.

Why this pattern fits:
With positive numbers, if some window of length L reaches the target then some window of length L + 1 does too.
That monotonicity allows bisecting the length (the O(n log n) follow-up to the sliding-window solution).

Approach:
Search L in [1, n]. For a candidate, slide a fixed window of size L and check whether any sum reaches the target.

Worked Example:
target = 7, nums = [2, 3, 1, 2, 4, 3] -> length 2 works with [4, 3]; length 1 does not. Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(1)
*/

public class P29_MinimumSizeSubarraySumBinarySearchAnswer {
    private static boolean windowReaches(int[] nums, int length, int target) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= length) {
                sum -= nums[i - length];
            }
            if (i >= length - 1 && sum >= target) {
                return true;
            }
        }
        return false;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (!windowReaches(nums, nums.length, target)) {
            return 0;
        }
        int low = 1;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (windowReaches(nums, mid, target)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("7, [2, 3, 1, 2, 4, 3] -> " + minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println("4, [1, 4, 4] -> " + minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println("11, [1, 1, 1, 1, 1, 1, 1, 1] -> " + minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
