import java.util.*;

/*
Problem: Sum of Absolute Differences in a Sorted Array
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given a sorted array nums, return an array where result[i] is the sum of |nums[i] - nums[j]| over all j.

Why this pattern fits:
In a sorted array, every element to the left is smaller and every element to the right is larger,
so the absolute values disappear and each side becomes count * nums[i] versus a range sum.

Approach:
With leftSum = sum of elements before i and rightSum = sum of elements after i:
result[i] = nums[i] * i - leftSum + rightSum - nums[i] * (n - 1 - i).

Worked Example:
nums = [2, 3, 5] -> for 3: (3 * 1 - 2) + (5 - 3 * 1) = 3. Answer = [4, 3, 5].

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P39_SumOfAbsoluteDifferencesInASortedArrayPrefixSum {
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int value : nums) {
            total += value;
        }
        int[] result = new int[n];
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            int rightSum = total - leftSum - nums[i];
            result[i] = nums[i] * i - leftSum + rightSum - nums[i] * (n - 1 - i);
            leftSum += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[2, 3, 5] -> " + Arrays.toString(getSumAbsoluteDifferences(new int[]{2, 3, 5})));
        System.out.println("[1, 4, 6, 8, 10] -> " + Arrays.toString(getSumAbsoluteDifferences(new int[]{1, 4, 6, 8, 10})));
    }
}
