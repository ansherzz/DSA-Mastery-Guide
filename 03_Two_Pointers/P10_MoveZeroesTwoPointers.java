import java.util.*;

/*
Problem: Move Zeroes
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given an integer array nums, move all 0s to the end of it while maintaining the relative order
    of the non-zero elements. You must do this in place without making a copy of the array.

Why this pattern fits:
This is the read/write (slow/fast) two-pointer template.
    One pointer reads every element; the other marks where the next kept element should be written.

Approach:
The write pointer marks the boundary: everything before it is the final sequence of non-zero values.
    The read pointer scans the array. Whenever it sees a non-zero value, swap it with the write position
    and advance the write pointer.
    Swapping (rather than overwriting) pushes the zeros toward the end automatically.

Worked Example:
Example:
    nums = [0, 1, 0, 3, 12]
    1 swaps to index 0, 3 swaps to index 1, 12 swaps to index 2. Result: [1, 3, 12, 0, 0].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P10_MoveZeroesTwoPointers {
    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                int temp = nums[write];
                nums[write] = nums[read];
                nums[read] = temp;
                write++;
            }
        }
    }

    public static void main(String[] args) {
        int[] first = {0, 1, 0, 3, 12};
        moveZeroes(first);
        System.out.println(Arrays.toString(first));

        int[] second = {0, 0, 1};
        moveZeroes(second);
        System.out.println(Arrays.toString(second));
    }
}
