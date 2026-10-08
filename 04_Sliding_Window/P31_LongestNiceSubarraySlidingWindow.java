import java.util.*;

/*
Problem: Longest Nice Subarray
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
You are given an array nums of positive integers.
    A subarray is called nice if the bitwise AND of every pair of elements in different positions is 0.
    Return the length of the longest nice subarray. Subarrays of length 1 are always nice.

Why this pattern fits:
"Every pair has AND 0" means no bit is set in more than one element of the window.
    That property can only break when a new element is added and can only be repaired by removing from the left,
    which is exactly the variable-size window shape.

Approach:
Keep usedBits, the OR of all elements in the window. Because no bit is shared, the OR is also the sum of the bits.
    When a new element shares a bit with usedBits, shrink from the left:
    removing an element clears exactly its bits, which XOR does because its bits are present exactly once.
    Then add the new element's bits and update the best length.

Worked Example:
Example:
    nums = [1, 3, 8, 48, 10]
    The window [3, 8, 48] is 00011, 01000, 110000 in binary with no shared bits. Adding 10 (01010) conflicts.
    Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P31_LongestNiceSubarraySlidingWindow {
    public static int longestNiceSubarray(int[] nums) {
        int usedBits = 0;
        int left = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            while ((usedBits & nums[right]) != 0) {
                usedBits ^= nums[left++];
            }
            usedBits |= nums[right];
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(longestNiceSubarray(new int[]{1, 3, 8, 48, 10}));
        System.out.println(longestNiceSubarray(new int[]{3, 1, 5, 11, 13}));
    }
}
