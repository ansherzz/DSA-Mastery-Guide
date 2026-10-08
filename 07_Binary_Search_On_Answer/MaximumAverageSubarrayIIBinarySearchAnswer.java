import java.util.*;

/*
Problem: Maximum Average Subarray II
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
You are given an integer array nums of n elements and an integer k.
    Find a contiguous subarray whose length is greater than or equal to k that has the maximum average value,
    and return this value. Any answer with a calculation error less than 10^-5 is accepted.

Why this pattern fits:
The length is not fixed, so a sliding window cannot be used directly.
    But the question "is there a subarray of length >= k with average >= x?" is monotonic in x,
    and it can be answered in O(n). The search runs over real numbers until the interval is small enough.

Approach:
Average >= x is the same as sum of (nums[i] - x) >= 0, which removes the division by a variable length.
    To test x, scan with a running prefix of (nums[i] - x) and keep the minimum prefix that ends at least k positions back.
    If current prefix - that minimum is ever >= 0, a qualifying subarray exists.
    Binary search x between the minimum and maximum element until the range is below 10^-5.

Worked Example:
Example:
    nums = [1, 12, -5, -6, 50, 3], k = 4
    The subarray [12, -5, -6, 50] has average 12.75, and the longer subarrays have smaller averages. Answer = 12.75.

Time Complexity: O(n log((max - min) / precision))
Space Complexity: O(1)
*/

public class MaximumAverageSubarrayIIBinarySearchAnswer {
    public static double findMaxAverage(int[] nums, int k) {
        double low = Integer.MAX_VALUE;
        double high = Integer.MIN_VALUE;
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }

        while (high - low > 1e-6) {
            double mid = (low + high) / 2;
            if (hasAverageAtLeast(nums, k, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static boolean hasAverageAtLeast(int[] nums, int k, double target) {
        double prefix = 0;
        double trailing = 0;
        double minTrailing = 0;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i] - target;
            if (i >= k) {
                trailing += nums[i - k] - target;
                minTrailing = Math.min(minTrailing, trailing);
            }
            if (i >= k - 1 && prefix - minTrailing >= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.printf("%.5f%n", findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.printf("%.5f%n", findMaxAverage(new int[]{5}, 1));
        System.out.printf("%.5f%n", findMaxAverage(new int[]{-1, -2, -3, -4}, 2));
    }
}
