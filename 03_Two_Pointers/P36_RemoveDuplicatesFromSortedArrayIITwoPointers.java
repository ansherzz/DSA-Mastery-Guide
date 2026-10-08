import java.util.*;

/*
Problem: Remove Duplicates from Sorted Array II
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given a sorted integer array, remove duplicates in-place so that each unique value appears at most twice.
    Keep the relative order. Return k, the number of elements kept; the first k slots must hold the result.
    Use only constant extra space.

Why this pattern fits:
This extends the read/write pointer idea from the basic version.
    Because the array is sorted, comparing against the element two positions behind the write pointer
    is enough to know whether a third copy is about to be written.

Approach:
The first two elements are always kept.
    For every later element, write it only if it differs from the element at write - 2.
    If it equals that element, the kept prefix already contains two copies, so skip it.

Worked Example:
Example:
    nums = [1, 1, 1, 2, 2, 3]
    The third 1 is skipped. Result prefix = [1, 1, 2, 2, 3], k = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P36_RemoveDuplicatesFromSortedArrayIITwoPointers {
    public static int removeDuplicates(int[] nums) {
        int write = 0;
        for (int value : nums) {
            if (write < 2 || value != nums[write - 2]) {
                nums[write++] = value;
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
        runDemo(new int[]{1, 1, 1, 2, 2, 3});
        runDemo(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3});
    }
}
