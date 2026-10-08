import java.util.*;

/*
Problem: Find First and Last Position of Element in Sorted Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given a sorted array and a target value, return the starting and ending position of the target.
    If the target is not present, return {-1, -1}.

    The standard approach is to run binary search twice: once for the left boundary and once for the right boundary.

Why this pattern fits:
Boundary questions are where candidates start to truly understand binary search.
    Exact lookup is easy; locating the full range is more interview-representative.

Approach:
Implement lowerBound for the first index with value >= target.
    Implement upperBound for the first index with value > target.
    If the target exists, the answer is [lowerBound, upperBound - 1].

Worked Example:
Example:
    nums = [5, 7, 7, 8, 8, 10], target = 8
    The answer is [3, 4].

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P27_FindFirstAndLastPositionBinarySearch {
    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int[] searchRange(int[] nums, int target) {
        int start = lowerBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        int end = upperBound(nums, target) - 1;
        return new int[]{start, end};
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Range  : " + Arrays.toString(searchRange(nums, target)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 7, 7, 8, 8, 10}, 8);
        runDemo(new int[]{5, 7, 7, 8, 8, 10}, 6);
    }
}
