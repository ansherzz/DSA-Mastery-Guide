import java.util.*;

/*
Problem: Maximum Average Subarray I
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
Given an integer array nums and an integer k, find the contiguous subarray of length exactly k
    that has the maximum average value, and return that average.

Why this pattern fits:
The window length is fixed. When the window slides one step, only one value leaves and one value enters,
    so the sum can be updated in O(1) instead of being recomputed.

Approach:
Compute the sum of the first k elements.
    Slide the window: add the entering element and subtract the leaving element.
    Track the largest window sum and divide by k at the end.

Worked Example:
Example:
    nums = [1, 12, -5, -6, 50, 3], k = 4
    Window sums: 2, 51, 42. The best is 51, so the average is 51 / 4 = 12.75.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MaximumAverageSubarraySlidingWindow {
    public static double findMaxAverage(int[] nums, int k) {
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        long best = windowSum;
        for (int right = k; right < nums.length; right++) {
            windowSum += nums[right] - nums[right - k];
            best = Math.max(best, windowSum);
        }
        return (double) best / k;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array  : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Average: " + findMaxAverage(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 12, -5, -6, 50, 3}, 4);
        runDemo(new int[]{5}, 1);
    }
}
