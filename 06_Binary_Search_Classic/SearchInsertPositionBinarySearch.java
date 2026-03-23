import java.util.*;

/*
Problem: Search Insert Position
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted array of distinct integers and a target, return the index if the target is found.
    If not found, return the index where it should be inserted to keep the array sorted.

    This is boundary-finding binary search.

Why this pattern fits:
The answer is not just an existing position; it may be the first place where the target could fit.
    That makes this a classic lower-bound style problem.

Approach:
Use binary search on the sorted array.
    When the loop ends, left is the first index where the target can be inserted.

Worked Example:
Example:
    nums = [1, 3, 5, 6], target = 2
    The answer is 1 because 2 should be inserted before 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class SearchInsertPositionBinarySearch {
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Insert : " + searchInsert(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 5, 6}, 5);
        runDemo(new int[]{1, 3, 5, 6}, 2);
        runDemo(new int[]{1, 3, 5, 6}, 7);
    }
}
