import java.util.*;

/*
Problem: Maximum Count of Positive Integer and Negative Integer
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given an array nums sorted in non-decreasing order, return the maximum between
    the number of positive integers and the number of negative integers. Zero is neither positive nor negative.

Why this pattern fits:
In a sorted array the negatives form a prefix and the positives form a suffix.
    Finding where each block ends is a boundary search, which is the lower-bound template.

Approach:
The number of negatives is the index of the first element >= 0, which is lowerBound(0).
    The number of positives is n minus the index of the first element >= 1, which is n - lowerBound(1).
    Return the larger of the two counts.

Worked Example:
Example:
    nums = [-3, -2, -1, 0, 0, 1, 2]
    lowerBound(0) = 3 negatives. lowerBound(1) = 5, so 7 - 5 = 2 positives. Answer = 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P13_MaximumCountOfPositiveAndNegativeBinarySearch {
    public static int maximumCount(int[] nums) {
        int negatives = lowerBound(nums, 0);
        int positives = nums.length - lowerBound(nums, 1);
        return Math.max(negatives, positives);
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(maximumCount(new int[]{-2, -1, -1, 1, 2, 3}));
        System.out.println(maximumCount(new int[]{-3, -2, -1, 0, 0, 1, 2}));
        System.out.println(maximumCount(new int[]{5, 20, 66, 1314}));
        System.out.println(maximumCount(new int[]{0, 0}));
    }
}
