import java.util.*;

/*
Problem: Running Sum of 1D Array
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given an integer array, return a new array where each position contains the sum of all elements up to that index.

    This is the smallest useful prefix-sum exercise and builds intuition for the rest of the pattern.

Why this pattern fits:
Prefix sum is about carrying cumulative information forward.
    This file is intentionally simple so the technique feels mechanical and reliable.

Approach:
Create an output array.
    Set answer[i] = answer[i - 1] + nums[i], with a base case at index 0.

Worked Example:
Example:
    nums = [1, 2, 3, 4]
    The running sum array is [1, 3, 6, 10].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P01_RunningSumPrefixSum {
    public static int[] runningSum(int[] nums) {
        int[] prefix = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }
        return prefix;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Prefix : " + Arrays.toString(runningSum(nums)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4});
        runDemo(new int[]{1, 1, 1, 1, 1});
    }
}
