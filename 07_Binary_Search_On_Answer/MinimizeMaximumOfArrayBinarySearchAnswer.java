import java.util.*;

/*
Problem: Minimize Maximum of Array
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given a 0-indexed array nums of n non-negative integers. In one operation, you must:
    choose an index i with 1 <= i < n and nums[i] > 0, decrease nums[i] by 1, and increase nums[i - 1] by 1.
    Return the minimum possible value of the maximum element of nums after any number of operations.

Why this pattern fits:
The answer is a number, and feasibility is monotonic: if the maximum can be kept at most M,
    it can also be kept at most M + 1. Checking one candidate M is a simple linear scan.

Approach:
Operations only move value to the left, so a prefix can absorb excess from the elements inside it but never from outside.
    A candidate M is feasible exactly when every prefix of length i + 1 has sum at most M * (i + 1).
    Binary search M between 0 and the largest element, and keep the smallest feasible value.

Worked Example:
Example:
    nums = [3, 7, 1, 6]
    Prefix sums: 3, 10, 11, 17. With M = 5 the limits are 5, 10, 15, 20, so M = 5 is feasible.
    With M = 4 the limit for the second prefix is 8 < 10, so it is not feasible. Answer = 5.

Time Complexity: O(n log(max value))
Space Complexity: O(1)
*/

public class MinimizeMaximumOfArrayBinarySearchAnswer {
    public static int minimizeArrayValue(int[] nums) {
        int low = 0;
        int high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canKeepAtMost(nums, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean canKeepAtMost(int[] nums, int limit) {
        long prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (prefix > (long) limit * (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minimizeArrayValue(new int[]{3, 7, 1, 6}));
        System.out.println(minimizeArrayValue(new int[]{10, 1}));
    }
}
