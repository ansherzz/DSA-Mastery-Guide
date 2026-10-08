import java.util.*;

/*
Problem: Find Minimum in Rotated Sorted Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
A sorted array has been rotated some number of times. Return the minimum element.
    Assume all values are distinct.

    The goal is not to find an index directly, but to locate the inflection point of the rotation.

Why this pattern fits:
If nums[mid] is greater than nums[right], the minimum must be to the right of mid.
    Otherwise the minimum is at mid or to the left.

Approach:
Use binary search on the property that the right side around the minimum is sorted and smaller.
    Shrink the range until left == right.

Worked Example:
Example:
    nums = [3, 4, 5, 1, 2]
    The minimum is 1.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P03_FindMinimumInRotatedSortedArrayAdvanced {
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Min    : " + findMin(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 4, 5, 1, 2});
        runDemo(new int[]{4, 5, 6, 7, 0, 1, 2});
    }
}
