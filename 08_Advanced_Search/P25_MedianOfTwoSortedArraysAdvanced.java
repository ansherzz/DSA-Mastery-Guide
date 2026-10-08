import java.util.*;

/*
Problem: Median of Two Sorted Arrays
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
Given two sorted arrays, return the median of the combined sorted order in O(log(min(m, n))) time.

    The advanced idea is to binary search the partition of the smaller array so that left partitions contain
    exactly half of the total elements.

Why this pattern fits:
This is a classic partition-based binary search problem.
    Instead of searching values, you search where to cut the two arrays so the left side and right side are balanced.

Approach:
Always binary search the smaller array.
    Let partitionA determine partitionB. The partition is correct when
    maxLeftA <= minRightB and maxLeftB <= minRightA.

Worked Example:
Example:
    nums1 = [1, 3], nums2 = [2]
    The median is 2.0.

Time Complexity: O(log(min(m, n)))
Space Complexity: O(1)
*/

public class P25_MedianOfTwoSortedArraysAdvanced {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int total = nums1.length + nums2.length;
        int half = (total + 1) / 2;

        int left = 0;
        int right = nums1.length;

        while (left <= right) {
            int cut1 = left + (right - left) / 2;
            int cut2 = half - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int right1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if (total % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                right = cut1 - 1;
            } else {
                left = cut1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted correctly.");
    }

    private static void runDemo(int[] nums1, int[] nums2) {
        System.out.println("nums1  : " + Arrays.toString(nums1));
        System.out.println("nums2  : " + Arrays.toString(nums2));
        System.out.println("Median : " + findMedianSortedArrays(nums1, nums2));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3}, new int[]{2});
        runDemo(new int[]{1, 2}, new int[]{3, 4});
    }
}
