import java.util.*;

/*
Problem: Rotate Array with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an integer array and a non-negative integer k, rotate the array to the right by k steps.
    The rotation must be done in-place.

    Start with the most literal interpretation: a rotation by k is simply k rotations by one.

Why this pattern fits:
The statement describes a repeated simple action. Simulating that action exactly as described
    gives a correct baseline, and it makes the later reversal trick easier to appreciate.

Approach:
Reduce k with k % n because n rotations return the original array.
    Then repeat k times: remember the last element, shift everything one step right,
    and place the remembered element at index 0.

Worked Example:
Example:
    nums = [1, 2, 3, 4, 5, 6, 7], k = 3
    After 1 step: [7, 1, 2, 3, 4, 5, 6]
    After 2 steps: [6, 7, 1, 2, 3, 4, 5]
    After 3 steps: [5, 6, 7, 1, 2, 3, 4]

Time Complexity: O(n * k)
Space Complexity: O(1)
*/

public class RotateArrayBruteForce {
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }

        k %= n;
        for (int step = 0; step < k; step++) {
            int last = nums[n - 1];
            for (int i = n - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = last;
        }
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Before: " + Arrays.toString(nums) + ", k = " + k);
        rotate(nums, k);
        System.out.println("After : " + Arrays.toString(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        runDemo(new int[]{-1, -100, 3, 99}, 2);
        runDemo(new int[]{1, 2}, 5);
    }
}
