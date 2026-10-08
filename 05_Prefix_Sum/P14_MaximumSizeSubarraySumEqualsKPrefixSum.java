import java.util.*;

/*
Problem: Maximum Size Subarray Sum Equals K
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array nums and an integer k, return the maximum length of a contiguous subarray
    whose sum equals k. If no such subarray exists, return 0. Values may be negative.

Why this pattern fits:
With negative numbers a sliding window breaks down, but the prefix-sum identity still holds:
    a subarray (i, j] sums to k when prefix[j] - prefix[i] == k.
    To make the subarray as long as possible, you want the earliest index where prefix[j] - k occurred.

Approach:
Store the first index at which each prefix sum appears, with prefix 0 stored at index -1.
    For each position, look up prefix - k. If it exists, the subarray after that earlier index has sum k.
    Never overwrite an existing entry, because the earliest index gives the longest subarray.

Worked Example:
Example:
    nums = [1, -1, 5, -2, 3], k = 3
    Prefix sums: 1, 0, 5, 3, 6.
    At index 3 the prefix is 3, and prefix 0 was first seen at index -1, giving length 4.
    The subarray is [1, -1, 5, -2].

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P14_MaximumSizeSubarraySumEqualsKPrefixSum {
    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1);

        long prefix = 0;
        int best = 0;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];

            Integer earlier = firstIndex.get(prefix - k);
            if (earlier != null) {
                best = Math.max(best, i - earlier);
            }
            firstIndex.putIfAbsent(prefix, i);
        }
        return best;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("k      : " + k);
        System.out.println("Length : " + maxSubArrayLen(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, -1, 5, -2, 3}, 3);
        runDemo(new int[]{-2, -1, 2, 1}, 1);
        runDemo(new int[]{1, 2, 3}, 10);
    }
}
