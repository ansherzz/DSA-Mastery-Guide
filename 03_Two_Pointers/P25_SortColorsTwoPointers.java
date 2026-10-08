import java.util.*;

/*
Problem: Sort Colors (Dutch National Flag)
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an array containing only the values 0, 1, and 2 (representing red, white, and blue),
    sort it in-place so that equal values are adjacent and the order is 0s, then 1s, then 2s.
    Do it in one pass with constant extra space and without calling a library sort.

Why this pattern fits:
There are only three values, so the array can be split into regions:
    a finished 0 region at the front, a finished 2 region at the back, and an unknown region in between.
    Pointers mark the region boundaries and shrink the unknown region.

Approach:
Keep low (next slot for 0), high (next slot for 2), and mid (current element).
    If nums[mid] is 0, swap it to low and advance both low and mid.
    If nums[mid] is 1, it is already in the middle region, so advance mid.
    If nums[mid] is 2, swap it to high and shrink high, but do not advance mid because the swapped-in value is unchecked.

Worked Example:
Example:
    nums = [2, 0, 2, 1, 1, 0]
    After processing: [0, 0, 1, 1, 2, 2]

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P25_SortColorsTwoPointers {
    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Before: " + Arrays.toString(nums));
        sortColors(nums);
        System.out.println("After : " + Arrays.toString(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 0, 2, 1, 1, 0});
        runDemo(new int[]{2, 0, 1});
        runDemo(new int[]{1, 2, 0, 2, 1, 0, 0});
    }
}
