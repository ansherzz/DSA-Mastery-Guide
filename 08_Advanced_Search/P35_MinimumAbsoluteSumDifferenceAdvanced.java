import java.util.*;

/*
Problem: Minimum Absolute Sum Difference
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given two positive integer arrays nums1 and nums2, both of length n.
    The absolute sum difference is the sum of |nums1[i] - nums2[i]| over all i.
    You may replace at most one element of nums1 with any other element of nums1 to minimize the absolute sum difference.
    Return the minimum absolute sum difference after the replacement, modulo 10^9 + 7.

Why this pattern fits:
For each index, the best replacement is the element of nums1 closest to nums2[i].
    "Closest value in a collection" is a lower-bound search on a sorted copy, checking the neighbor on each side.

Approach:
Sort a copy of nums1. Compute the original total.
    For each index i, find the lower bound of nums2[i] in the sorted copy.
    The closest value is either the element at that position or the one just before it.
    The saving for index i is the original difference minus the closest achievable difference.
    Subtract the largest saving from the total, and apply the modulus only at the end.

Worked Example:
Example:
    nums1 = [1, 7, 5], nums2 = [2, 3, 5]
    Original total = 1 + 4 + 0 = 5. Replacing the 7 with 1 or 5 changes its difference from 4 to 2.
    Best saving = 2, so the answer is 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P35_MinimumAbsoluteSumDifferenceAdvanced {
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);

        long total = 0;
        int bestSaving = 0;
        for (int i = 0; i < n; i++) {
            int difference = Math.abs(nums1[i] - nums2[i]);
            total += difference;

            int index = lowerBound(sorted, nums2[i]);
            int closest = difference;
            if (index < n) {
                closest = Math.min(closest, sorted[index] - nums2[i]);
            }
            if (index > 0) {
                closest = Math.min(closest, nums2[i] - sorted[index - 1]);
            }
            bestSaving = Math.max(bestSaving, difference - closest);
        }
        return (int) ((total - bestSaving) % 1_000_000_007L);
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff(new int[]{1, 7, 5}, new int[]{2, 3, 5}));
        System.out.println(minAbsoluteSumDiff(new int[]{2, 4, 6, 8, 10}, new int[]{2, 4, 6, 8, 10}));
        System.out.println(minAbsoluteSumDiff(new int[]{1, 10, 4, 4, 2, 7}, new int[]{9, 3, 5, 1, 7, 4}));
    }
}
