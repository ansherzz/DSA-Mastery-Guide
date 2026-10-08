import java.util.*;

/*
Problem: Find the Smallest Divisor Given a Threshold
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given an array of positive integers nums and an integer threshold, choose a positive integer divisor.
    Divide every element by the divisor, round each result up to the nearest integer, and add the results.
    Return the smallest divisor for which that sum is less than or equal to threshold.
    A valid answer is guaranteed to exist.

Why this pattern fits:
You are not searching the array; you are searching the space of possible divisors.
    A larger divisor never increases the sum, so feasibility flips from false to true exactly once.

Approach:
Search the divisor between 1 and the maximum element (a divisor that large makes every term equal to 1).
    For a candidate divisor, compute the rounded-up sum in one pass.
    If the sum fits within the threshold, try a smaller divisor; otherwise try a larger one.

Worked Example:
Example:
    nums = [1, 2, 5, 9], threshold = 6
    Divisor 4 gives 1 + 1 + 2 + 3 = 7, which is too large.
    Divisor 5 gives 1 + 1 + 1 + 2 = 5, which fits. Answer = 5.

Time Complexity: O(n log(max(nums)))
Space Complexity: O(1)
*/

public class P05_SmallestDivisorGivenThresholdBinarySearchAnswer {
    private static boolean fits(int[] nums, int threshold, int divisor) {
        long sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum <= threshold;
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = 0;
        for (int num : nums) {
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (fits(nums, threshold, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] nums, int threshold) {
        System.out.println("Nums      : " + Arrays.toString(nums));
        System.out.println("Threshold : " + threshold);
        System.out.println("Divisor   : " + smallestDivisor(nums, threshold));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 5, 9}, 6);
        runDemo(new int[]{44, 22, 33, 11, 1}, 5);
    }
}
