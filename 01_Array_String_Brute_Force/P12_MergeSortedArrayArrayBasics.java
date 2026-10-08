import java.util.*;

/*
Problem: Merge Sorted Array
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
You are given two sorted integer arrays nums1 and nums2, and two integers m and n.
    nums1 has length m + n: its first m values are real data and its last n slots are empty (zeros).
    Merge nums2 into nums1 so that nums1 becomes one sorted array. Do it in-place.

Why this pattern fits:
This is an array-manipulation fundamental. The naive idea (copy then sort) works,
    but thinking about which end of nums1 is free leads to a clean in-place fill.

Approach:
The free space is at the back of nums1, so fill from the back.
    Keep one pointer at the last real value of each array and one write pointer at the end of nums1.
    Repeatedly place the larger of the two candidates at the write position.
    Any leftovers in nums2 are copied; leftovers in nums1 are already in place.

Worked Example:
Example:
    nums1 = [1, 2, 3, 0, 0, 0], m = 3, nums2 = [2, 5, 6], n = 3
    Writing from the back gives [1, 2, 2, 3, 5, 6].

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

public class P12_MergeSortedArrayArrayBasics {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int write = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[write--] = nums1[i--];
            } else {
                nums1[write--] = nums2[j--];
            }
        }
    }

    private static void runDemo(int[] nums1, int m, int[] nums2, int n) {
        System.out.println("nums1 : " + Arrays.toString(nums1) + ", m = " + m);
        System.out.println("nums2 : " + Arrays.toString(nums2) + ", n = " + n);
        merge(nums1, m, nums2, n);
        System.out.println("Merged: " + Arrays.toString(nums1));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        runDemo(new int[]{0}, 0, new int[]{1}, 1);
        runDemo(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
    }
}
