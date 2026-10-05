import java.util.*;

/*
Problem: Minimum Value to Get Positive Step by Step Sum
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given an integer array nums, you choose a positive starting value startValue.
    You then add the elements of nums to it one by one, from left to right.
    Return the minimum positive startValue such that the running total never drops below 1.

Why this pattern fits:
The running total after step i is startValue + prefix[i].
    The tightest constraint comes from the smallest prefix sum, so only that one number matters.

Approach:
Compute the minimum prefix sum over the array (treating the empty prefix as 0).
    startValue must satisfy startValue + minPrefix >= 1, so startValue = 1 - minPrefix.
    Because the empty prefix is included, the result is always at least 1.

Worked Example:
Example:
    nums = [-3, 2, -3, 4, 2]
    Prefix sums: -3, -1, -4, 0, 2. The minimum is -4.
    startValue = 1 - (-4) = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MinimumStartValuePrefixSum {
    public static int minStartValue(int[] nums) {
        int prefix = 0;
        int minPrefix = 0;

        for (int num : nums) {
            prefix += num;
            minPrefix = Math.min(minPrefix, prefix);
        }
        return 1 - minPrefix;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums       : " + Arrays.toString(nums));
        System.out.println("Start value: " + minStartValue(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-3, 2, -3, 4, 2});
        runDemo(new int[]{1, 2});
        runDemo(new int[]{1, -2, -3});
    }
}
