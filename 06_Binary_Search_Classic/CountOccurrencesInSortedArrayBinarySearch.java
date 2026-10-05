import java.util.*;

/*
Problem: Count Occurrences of a Target in a Sorted Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given an integer array sorted in non-decreasing order and a target value,
    return how many times the target appears in the array. The solution must run in O(log n) time.

Why this pattern fits:
All copies of the target sit next to each other in a sorted array.
    Their count is the distance between two boundaries: the lower bound (first index >= target)
    and the upper bound (first index > target). Both are standard binary searches.

Approach:
Write one helper that returns the first index whose value is >= a given key.
    lowerBound(target) is the start of the block and lowerBound(target + 1) is the position just past it.
    The count is the difference between the two.

Worked Example:
Example:
    nums = [1, 2, 2, 2, 3, 5], target = 2
    First index >= 2 is 1. First index >= 3 is 4. Count = 4 - 1 = 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class CountOccurrencesInSortedArrayBinarySearch {
    public static int countOccurrences(int[] nums, int target) {
        return lowerBound(nums, (long) target + 1) - lowerBound(nums, target);
    }

    private static int lowerBound(int[] nums, long key) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Count  : " + countOccurrences(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 2, 2, 3, 5}, 2);
        runDemo(new int[]{1, 2, 2, 2, 3, 5}, 4);
        runDemo(new int[]{7, 7, 7, 7}, 7);
    }
}
