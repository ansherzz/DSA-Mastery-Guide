import java.util.*;

/*
Problem: Move Zeroes
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an integer array, move all zeroes to the end while keeping the relative order of non-zero values.
    Perform the work in-place.

    This is an array-manipulation foundation problem. It is not pure brute force, but it belongs in the early
    array basics set because it trains careful in-place updates and edge-case handling.

Why this pattern fits:
Many interview mistakes happen because candidates know the idea but lose track of write positions.
    This problem is great practice for disciplined array mutation.

Approach:
Maintain a write pointer for the next non-zero element.
    First copy all non-zero values forward.
    Then fill the remaining positions with zeroes.

Worked Example:
Example:
    nums = [0, 1, 0, 3, 12]
    After processing, nums becomes [1, 3, 12, 0, 0].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P03_MoveZeroesArrayBasics {
    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[write++] = num;
            }
        }
        while (write < nums.length) {
            nums[write++] = 0;
        }
    }

    private static void runDemo(int[] nums) {
        System.out.println("Before: " + Arrays.toString(nums));
        moveZeroes(nums);
        System.out.println("After : " + Arrays.toString(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{0, 1, 0, 3, 12});
        runDemo(new int[]{0, 0, 1});
    }
}
