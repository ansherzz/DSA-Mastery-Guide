import java.util.*;

/*
Problem: Find Minimum and Maximum
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Given a non-empty array of integers, return both its minimum and its maximum.
    Follow-up: a simple scan uses about 2n comparisons. Can you use fewer?

Why this pattern fits:
The answer for a range can be assembled from the answers for its two halves with just two comparisons:
    the smaller of the two minimums and the larger of the two maximums.
    This "solve halves, combine in O(1)" shape is the simplest possible divide and conquer.

Approach:
Base cases: a range of one element is both its own minimum and maximum (no comparisons);
    a range of two elements needs one comparison.
    Otherwise split the range in the middle, solve both halves, and combine with two comparisons.
    The recurrence T(n) = 2T(n / 2) + 2 gives about 3n / 2 - 2 comparisons, compared with 2n - 2 for the naive scan.

Worked Example:
Example:
    nums = [7, 2, 9, 4, 1, 8]
    Left half [7, 2, 9] -> (2, 9). Right half [4, 1, 8] -> (1, 8). Combined -> (1, 9).

Time Complexity: O(n)
Space Complexity: O(log n) recursion stack
*/

public class FindMinAndMaxDivideConquer {
    public static int[] minAndMax(int[] nums) {
        return solve(nums, 0, nums.length - 1);
    }

    private static int[] solve(int[] nums, int left, int right) {
        if (left == right) {
            return new int[]{nums[left], nums[left]};
        }
        if (right == left + 1) {
            return nums[left] < nums[right]
                ? new int[]{nums[left], nums[right]}
                : new int[]{nums[right], nums[left]};
        }

        int mid = left + (right - left) / 2;
        int[] leftResult = solve(nums, left, mid);
        int[] rightResult = solve(nums, mid + 1, right);
        return new int[]{
            Math.min(leftResult[0], rightResult[0]),
            Math.max(leftResult[1], rightResult[1])};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(minAndMax(new int[]{7, 2, 9, 4, 1, 8})));
        System.out.println(Arrays.toString(minAndMax(new int[]{5})));
        System.out.println(Arrays.toString(minAndMax(new int[]{-3, -3, -7})));
    }
}
