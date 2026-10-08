import java.util.*;

/*
Problem: Rotation Count in a Rotated Sorted Array
Pattern: 08. Advanced Search
Difficulty: Easy

Interview Prompt:
A sorted array of distinct integers was rotated to the right some number of times.
Return how many times it was rotated.

Why this pattern fits:
Each right rotation moves the minimum one step to the right, so the answer is the minimum's index.
Finding the minimum of a rotated array is a binary search against the last element.

Approach:
While low < high: if nums[mid] > nums[high] the minimum is to the right of mid, otherwise it is at mid or to the left.

Worked Example:
nums = [15, 18, 2, 3, 6, 12] -> the minimum 2 is at index 2, so the array was rotated twice.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P02_RotationCountInRotatedSortedArrayAdvanced {
    public static int rotationCount(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[15, 18, 2, 3, 6, 12] -> " + rotationCount(new int[]{15, 18, 2, 3, 6, 12}));
        System.out.println("[7, 9, 11, 12, 5] -> " + rotationCount(new int[]{7, 9, 11, 12, 5}));
        System.out.println("[1, 2, 3] -> " + rotationCount(new int[]{1, 2, 3}));
    }
}
