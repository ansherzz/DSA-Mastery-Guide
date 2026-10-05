import java.util.*;

/*
Problem: Maximum Subarray using Divide and Conquer
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given a non-empty integer array, find the contiguous subarray with the largest sum and return that sum.

    The brute-force version is in folder 01 and the prefix-sum version is in folder 05.
    Here the interviewer asks specifically for the divide-and-conquer solution.

Why this pattern fits:
Split the array at the middle. The best subarray is either entirely in the left half,
    entirely in the right half, or it crosses the middle. The first two are recursive calls,
    and the crossing case can be computed directly in linear time.

Approach:
Base case: a single element is its own best subarray.
    Recursively solve the left and right halves.
    For the crossing case, find the best sum that ends at the middle (scanning leftward)
    and the best sum that starts just after the middle (scanning rightward), and add them.
    Return the largest of the three.

Worked Example:
Example:
    nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    The best subarray [4, -1, 2, 1] crosses the midpoint: the best suffix of the left half is 4 + (-1) = 3
    and the best prefix of the right half is 2 + 1 = 3, giving 6.

Time Complexity: O(n log n)
Space Complexity: O(log n) recursion stack
*/

public class MaximumSubarrayDivideConquer {
    public static int maxSubArray(int[] nums) {
        return solve(nums, 0, nums.length - 1);
    }

    private static int solve(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        int bestLeft = solve(nums, left, mid);
        int bestRight = solve(nums, mid + 1, right);

        int sum = 0;
        int bestSuffix = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            bestSuffix = Math.max(bestSuffix, sum);
        }

        sum = 0;
        int bestPrefix = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            bestPrefix = Math.max(bestPrefix, sum);
        }

        return Math.max(Math.max(bestLeft, bestRight), bestSuffix + bestPrefix);
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums = " + Arrays.toString(nums) + " -> best sum " + maxSubArray(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        runDemo(new int[]{5, 4, -1, 7, 8});
        runDemo(new int[]{-3, -1, -2});
    }
}
