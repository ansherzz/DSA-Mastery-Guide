import java.util.*;

/*
Problem: Minimum Common Value
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given two arrays sorted in non-decreasing order, return the smallest integer common to both, or -1.

Why this pattern fits:
Because nums1 is sorted, the first of its values found in nums2 is the minimum common value.
Each lookup in the sorted nums2 is a classic exact-match binary search.

Approach:
For each value of nums1 from left to right, binary search it in nums2 and return it on the first hit.
(Search the longer array and iterate the shorter one for the best bound.)

Worked Example:
nums1 = [1, 2, 3, 6], nums2 = [2, 3, 4, 5] -> 1 is absent, 2 is found. Answer = 2.

Time Complexity: O(m log n)
Space Complexity: O(1)
*/

public class P22_MinimumCommonValueBinarySearch {
    private static boolean contains(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid] == target) {
                return true;
            }
            if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static int getCommon(int[] nums1, int[] nums2) {
        for (int value : nums1) {
            if (contains(nums2, value)) {
                return value;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3], [2, 4] -> " + getCommon(new int[]{1, 2, 3}, new int[]{2, 4}));
        System.out.println("[1, 2, 3, 6], [2, 3, 4, 5] -> " + getCommon(new int[]{1, 2, 3, 6}, new int[]{2, 3, 4, 5}));
        System.out.println("[1, 3], [2, 4] -> " + getCommon(new int[]{1, 3}, new int[]{2, 4}));
    }
}
