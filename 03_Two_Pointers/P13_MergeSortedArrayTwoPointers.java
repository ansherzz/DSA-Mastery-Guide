import java.util.*;

/*
Problem: Merge Sorted Array
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
    and two integers m and n representing the number of valid elements in each.
    nums1 has length m + n, where the last n slots are empty placeholders.
    Merge nums2 into nums1 so that nums1 becomes one sorted array. Do it in place.

Why this pattern fits:
Both inputs are sorted, so one pointer per array is enough to always know the next element to place.
    The trick is the direction: filling from the back uses the empty space and never overwrites unread values.

Approach:
Keep pointer i at the last valid element of nums1, pointer j at the last element of nums2,
    and a write pointer at the last slot of nums1.
    Repeatedly write the larger of nums1[i] and nums2[j] at the write position and move that pointer left.
    If nums2 runs out first, the remaining nums1 prefix is already in place.

Worked Example:
Example:
    nums1 = [1, 2, 3, 0, 0, 0], m = 3, nums2 = [2, 5, 6], n = 3
    Write 6, 5, 3, 2, 2 from the back. Result: [1, 2, 2, 3, 5, 6].

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

public class P13_MergeSortedArrayTwoPointers {
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

    public static void main(String[] args) {
        int[] first = {1, 2, 3, 0, 0, 0};
        merge(first, 3, new int[]{2, 5, 6}, 3);
        System.out.println(Arrays.toString(first));

        int[] second = {4, 5, 6, 0, 0};
        merge(second, 3, new int[]{1, 2}, 2);
        System.out.println(Arrays.toString(second));

        int[] third = {0};
        merge(third, 0, new int[]{1}, 1);
        System.out.println(Arrays.toString(third));
    }
}
