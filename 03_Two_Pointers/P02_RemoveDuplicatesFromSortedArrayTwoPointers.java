import java.util.*;

/*
Problem: Remove Duplicates from Sorted Array
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a sorted array, remove duplicates in-place so each unique value appears only once.
    Return the number of unique elements. The first returned-length elements of the array should contain
    the final unique values.

    This is a standard read-pointer and write-pointer problem.

Why this pattern fits:
Because the array is sorted, duplicates are adjacent.
    That makes it easy to compress the array in one pass.

Approach:
Keep a write pointer for the next unique position.
    Scan from left to right, and whenever a new value appears, copy it to the write position and advance write.

Worked Example:
Example:
    nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
    The unique prefix becomes [0, 1, 2, 3, 4] and the returned length is 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P02_RemoveDuplicatesFromSortedArrayTwoPointers {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 1;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[read - 1]) {
                nums[write++] = nums[read];
            }
        }
        return write;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Before: " + Arrays.toString(nums));
        int k = removeDuplicates(nums);
        System.out.println("k     : " + k);
        System.out.println("Prefix: " + Arrays.toString(Arrays.copyOf(nums, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 2});
        runDemo(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }
}
