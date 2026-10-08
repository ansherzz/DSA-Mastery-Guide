import java.util.*;

/*
Problem: Count the Number of Fair Pairs
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given an array and two integers lower and upper, count pairs (i, j) with i < j
and lower <= nums[i] + nums[j] <= upper.

Why this pattern fits:
The pair count does not depend on order, so sort first.
For a fixed element the valid partners form a contiguous value range, counted by two boundary searches.

Approach:
Sort. For each index i, search only in (i, n): count = firstGreater(upper - nums[i]) - firstAtLeast(lower - nums[i]).

Worked Example:
nums = [0, 1, 7, 4, 4, 5], lower = 3, upper = 6 -> six pairs have sums 4, 4, 5, 5, 5, 6.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P41_CountTheNumberOfFairPairsBinarySearch {
    private static int firstAtLeast(int[] nums, int from, long target) {
        int low = from;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = firstAtLeast(nums, i + 1, (long) lower - nums[i]);
            int end = firstAtLeast(nums, i + 1, (long) upper - nums[i] + 1);
            count += end - start;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1, 7, 4, 4, 5], 3..6 -> " + countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6));
        System.out.println("[1, 7, 9, 2, 5], 11..11 -> " + countFairPairs(new int[]{1, 7, 9, 2, 5}, 11, 11));
    }
}
