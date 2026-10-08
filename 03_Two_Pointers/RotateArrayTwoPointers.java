import java.util.*;

/*
Problem: Rotate Array
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
    Do it in place with O(1) extra space.

Why this pattern fits:
The brute-force version shifts the array k times. The in-place solution is built entirely from
    one two-pointer primitive: reversing a range by swapping its two ends inward.

Approach:
Rotating right by k moves the last k elements to the front while keeping both blocks in order.
    Reverse the whole array: the two blocks are now in the right places but each is backwards.
    Reverse the first k elements, then reverse the remaining n - k elements to fix their internal order.
    Reduce k modulo n first, since rotating by n changes nothing.

Worked Example:
Example:
    nums = [1, 2, 3, 4, 5, 6, 7], k = 3
    Reverse all      -> [7, 6, 5, 4, 3, 2, 1]
    Reverse first 3  -> [5, 6, 7, 4, 3, 2, 1]
    Reverse the rest -> [5, 6, 7, 1, 2, 3, 4]

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class RotateArrayTwoPointers {
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }

        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }

    public static void main(String[] args) {
        int[] first = {1, 2, 3, 4, 5, 6, 7};
        rotate(first, 3);
        System.out.println(Arrays.toString(first));

        int[] second = {-1, -100, 3, 99};
        rotate(second, 6);
        System.out.println(Arrays.toString(second));
    }
}
