import java.util.*;

/*
Problem: Maximum Absolute Sum of Any Subarray
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Return the maximum absolute value of the sum of any (possibly empty) subarray of nums.

Why this pattern fits:
Every subarray sum is a difference of two prefix sums.
The largest absolute difference between any two prefixes is simply max prefix - min prefix.

Approach:
Track the running prefix together with the minimum and maximum prefix seen (starting from 0 for the empty prefix).

Worked Example:
nums = [1, -3, 2, 3, -4] -> prefixes 0, 1, -2, 0, 3, -1. Max 3, min -2. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P32_MaximumAbsoluteSumOfAnySubarrayPrefixSum {
    public static int maxAbsoluteSum(int[] nums) {
        int prefix = 0;
        int min = 0;
        int max = 0;
        for (int value : nums) {
            prefix += value;
            min = Math.min(min, prefix);
            max = Math.max(max, prefix);
        }
        return max - min;
    }

    public static void main(String[] args) {
        System.out.println("[1, -3, 2, 3, -4] -> " + maxAbsoluteSum(new int[]{1, -3, 2, 3, -4}));
        System.out.println("[2, -5, 1, -4, 3, -2] -> " + maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2}));
    }
}
