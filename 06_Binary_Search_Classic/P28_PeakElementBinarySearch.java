import java.util.*;

/*
Problem: Find Peak Element
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
A peak element is an element strictly greater than its neighbors.
    Given an array where adjacent values are never equal, return the index of any peak element.

    The array is not sorted, but the slope around mid still lets binary search discard half the search space.

Why this pattern fits:
If nums[mid] < nums[mid + 1], then a peak must exist on the right side.
    Otherwise a peak exists on the left side including mid.
    That monotonic-slope reasoning is the core of this variation.

Approach:
Binary search on the slope.
    Compare nums[mid] and nums[mid + 1] to decide which half must contain a peak.

Worked Example:
Example:
    nums = [1, 2, 3, 1]
    Index 2 is a valid answer because 3 is greater than both neighbors.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P28_PeakElementBinarySearch {
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums  : " + Arrays.toString(nums));
        int peak = findPeakElement(nums);
        System.out.println("Peak index: " + peak + ", value: " + nums[peak]);
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 1});
        runDemo(new int[]{1, 2, 1, 3, 5, 6, 4});
    }
}
