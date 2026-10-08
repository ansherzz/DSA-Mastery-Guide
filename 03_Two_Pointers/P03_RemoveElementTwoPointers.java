import java.util.*;

/*
Problem: Remove Element
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given an integer array nums and a value val, remove all occurrences of val in-place.
    Return k, the number of elements that are not equal to val.
    The first k positions of nums must hold those remaining elements. The order of the kept elements may change.

Why this pattern fits:
In-place removal means overwriting unwanted values with wanted ones.
    One pointer reads through the array while another marks where the next kept value should be written.

Approach:
Use a read pointer that visits every element and a write pointer that starts at 0.
    Every time the read pointer sees a value different from val, copy it to the write position and advance write.
    The final write position is k.

Worked Example:
Example:
    nums = [0, 1, 2, 2, 3, 0, 4, 2], val = 2
    Kept values in order: 0, 1, 3, 0, 4, so k = 5 and the prefix is [0, 1, 3, 0, 4].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P03_RemoveElementTwoPointers {
    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != val) {
                nums[write++] = nums[read];
            }
        }
        return write;
    }

    private static void runDemo(int[] nums, int val) {
        System.out.println("Before: " + Arrays.toString(nums) + ", val = " + val);
        int k = removeElement(nums, val);
        System.out.println("k     : " + k);
        System.out.println("Prefix: " + Arrays.toString(Arrays.copyOf(nums, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 2, 2, 3}, 3);
        runDemo(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2);
    }
}
