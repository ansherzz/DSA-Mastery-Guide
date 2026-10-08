import java.util.*;

/*
Problem: Check if Array Is Sorted and Rotated
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an array nums, return true if it was originally sorted in non-decreasing order
and then rotated some number of positions (possibly zero). Duplicates may exist.

Why this pattern fits:
Brute force would try every rotation and check sortedness, which is O(n^2).
A sorted-then-rotated array has at most one "drop" when you walk around it in a circle.

Approach:
Compare every element with its circular neighbor nums[(i + 1) % n].
Count the positions where the current value is larger than the next one.
The array qualifies exactly when that count is at most 1.

Worked Example:
nums = [3, 4, 5, 1, 2]
The only drop is 5 -> 1 (2 -> 3 on the wrap is fine). One drop, so the answer is true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P07_CheckIfArrayIsSortedAndRotatedArrayBasics {
    public static boolean check(int[] nums) {
        int drops = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                drops++;
            }
        }
        return drops <= 1;
    }

    public static void main(String[] args) {
        System.out.println("[3, 4, 5, 1, 2] -> " + check(new int[]{3, 4, 5, 1, 2}));
        System.out.println("[2, 1, 3, 4] -> " + check(new int[]{2, 1, 3, 4}));
        System.out.println("[1, 2, 3] -> " + check(new int[]{1, 2, 3}));
    }
}
