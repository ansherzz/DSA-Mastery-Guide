import java.util.*;

/*
Problem: Number of Ways to Split Array
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array nums of length n, a split at index i (0 <= i < n - 1) is valid if
    the sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
    The right part must contain at least one element.

    Return the number of valid splits.

Why this pattern fits:
Every split compares a prefix against the matching suffix.
    With the total known, the suffix is total - prefix, so each split is checked in O(1).

Approach:
Compute the total with a long to avoid overflow.
    Walk the split points from left to right while maintaining the left sum.
    Count the split when leftSum >= total - leftSum.

Worked Example:
Example:
    nums = [10, 4, -8, 7]
    Split after index 0: 10 >= 3, valid.
    Split after index 1: 14 >= -1, valid.
    Split after index 2: 6 >= 7 is false.
    Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P22_NumberOfWaysToSplitArrayPrefixSum {
    public static int waysToSplitArray(int[] nums) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }

        long leftSum = 0;
        int ways = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            if (leftSum >= total - leftSum) {
                ways++;
            }
        }
        return ways;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums : " + Arrays.toString(nums));
        System.out.println("Ways : " + waysToSplitArray(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{10, 4, -8, 7});
        runDemo(new int[]{2, 3, 1, 0});
    }
}
