import java.util.*;

/*
Problem: Search in Rotated Sorted Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
A sorted array is rotated at an unknown pivot. Given the rotated array and a target,
    return the target index or -1 if it does not exist. Assume all values are distinct.

    The array is not fully sorted, but one half is always sorted.

Why this pattern fits:
At every step, either the left half or the right half is guaranteed to be sorted.
    That is the hidden structure that keeps binary search alive after rotation.

Approach:
Compare nums[left], nums[mid], and nums[right].
    Determine which half is sorted, then decide whether the target lies inside that sorted half.

Worked Example:
Example:
    nums = [4, 5, 6, 7, 0, 1, 2], target = 0
    The answer is index 4.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P06_SearchInRotatedSortedArrayAdvanced {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Index  : " + search(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        runDemo(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
    }
}
