import java.util.*;

/*
Problem: Maximum Subarray using Prefix Sums
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given a non-empty integer array, find the contiguous subarray with the largest sum and return that sum.

    The brute-force version is in folder 01. Solve it here in O(n) by reasoning with prefix sums.

Why this pattern fits:
Any subarray sum equals prefix[j] - prefix[i] with i < j.
    For a fixed right end j, the sum is largest when prefix[i] is as small as possible.
    So you only need the current prefix and the minimum prefix seen before it.

Approach:
Keep a running prefix and the minimum prefix seen so far, starting from 0 for the empty prefix.
    For each element, update the prefix, compare prefix - minPrefix against the best answer,
    and only then update minPrefix so the subarray is never empty.

Worked Example:
Example:
    nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    Prefix sums: -2, -1, -4, 0, -1, 1, 2, -3, 1.
    The minimum prefix before index 6 is -4 and the prefix at index 6 is 2, giving 2 - (-4) = 6.
    That corresponds to the subarray [4, -1, 2, 1].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P21_MaximumSubarrayPrefixSum {
    public static int maxSubArray(int[] nums) {
        int prefix = 0;
        int minPrefix = 0;
        int best = Integer.MIN_VALUE;

        for (int num : nums) {
            prefix += num;
            best = Math.max(best, prefix - minPrefix);
            minPrefix = Math.min(minPrefix, prefix);
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums : " + Arrays.toString(nums));
        System.out.println("Best : " + maxSubArray(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        runDemo(new int[]{5, 4, -1, 7, 8});
        runDemo(new int[]{-3, -1, -2});
    }
}
