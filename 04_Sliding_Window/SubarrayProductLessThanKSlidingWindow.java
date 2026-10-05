import java.util.*;

/*
Problem: Subarray Product Less Than K
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array of positive integers nums and an integer k, return the number of contiguous subarrays
    whose product of all elements is strictly less than k.

Why this pattern fits:
All values are positive, so extending a window never decreases the product and shrinking never increases it.
    That monotonic behavior lets a window find, for each right end, the furthest valid left end.

Approach:
Expand the right pointer and multiply the new value into the running product.
    While the product is at least k, divide out the leftmost value and move left forward.
    Every subarray ending at right and starting anywhere from left to right is valid,
    so add (right - left + 1) to the count.

Worked Example:
Example:
    nums = [10, 5, 2, 6], k = 100
    Valid subarrays: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]. Answer = 8.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class SubarrayProductLessThanKSlidingWindow {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        long product = 1;
        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array: " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Count: " + numSubarrayProductLessThanK(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{10, 5, 2, 6}, 100);
        runDemo(new int[]{1, 2, 3}, 0);
    }
}
