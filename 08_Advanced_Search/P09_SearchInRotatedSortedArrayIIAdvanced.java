import java.util.*;

/*
Problem: Search in Rotated Sorted Array II
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
A sorted array is rotated at an unknown pivot and may contain duplicates.
    Return true if a target value exists in the array; otherwise return false.

    Duplicates make the sorted-half test ambiguous in some cases.

Why this pattern fits:
When nums[left] == nums[mid] == nums[right], you cannot tell which half is sorted.
    The safe fallback is to shrink both ends by one and continue.

Approach:
Use a modified binary search.
    If duplicates block the decision, move both boundaries inward.
    Otherwise use the usual rotated-array logic to discard half the space.

Worked Example:
Example:
    nums = [2, 5, 6, 0, 0, 1, 2], target = 0
    The answer is true.

Time Complexity: Average O(log n), worst-case O(n) due to duplicates
Space Complexity: O(1)
*/

public class P09_SearchInRotatedSortedArrayIIAdvanced {
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
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

        return false;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Found? : " + search(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 5, 6, 0, 0, 1, 2}, 0);
        runDemo(new int[]{2, 5, 6, 0, 0, 1, 2}, 3);
    }
}
