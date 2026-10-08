import java.util.*;

/*
Problem: Search in a Nearly Sorted Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
An array was sorted, but then some elements were displaced by at most one position:
    the element that belongs at index i may now be at index i - 1, i, or i + 1.
    Given such an array of distinct integers and a target, return the index of the target or -1 if it is absent.

    The solution must run in O(log n) time.

Why this pattern fits:
The array is not strictly sorted, so the usual single comparison at mid is not reliable.
    But the disorder is bounded: if the target is not at mid - 1, mid, or mid + 1,
    comparing with nums[mid] still tells you which side it is on, and you can safely skip two positions.

Approach:
At each step check mid, mid - 1, and mid + 1 for the target.
    If none match and nums[mid] is greater than the target, continue in [left, mid - 2].
    Otherwise continue in [mid + 2, right].

Worked Example:
Example:
    nums = [10, 3, 40, 20, 50, 80, 70], target = 40
    mid = 3 (value 20). The neighbor at mid - 1 is 40, so the answer is index 2.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P14_SearchInNearlySortedArrayAdvanced {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (mid - 1 >= left && nums[mid - 1] == target) {
                return mid - 1;
            }
            if (mid + 1 <= right && nums[mid + 1] == target) {
                return mid + 1;
            }

            if (nums[mid] > target) {
                right = mid - 2;
            } else {
                left = mid + 2;
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
        runDemo(new int[]{10, 3, 40, 20, 50, 80, 70}, 40);
        runDemo(new int[]{10, 3, 40, 20, 50, 80, 70}, 70);
        runDemo(new int[]{10, 3, 40, 20, 50, 80, 70}, 90);
    }
}
