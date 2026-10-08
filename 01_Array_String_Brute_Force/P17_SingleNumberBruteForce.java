import java.util.*;

/*
Problem: Single Number
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given a non-empty array of integers where every element appears exactly twice except for one,
    return the element that appears only once.

Why this pattern fits:
The definition of the answer can be checked directly: an element is the answer if no other index holds the same value.
    Writing that check first makes the later optimizations (hash set, then XOR) easy to justify.

Approach:
For every index i, scan the whole array and count how many times nums[i] appears.
    The first value whose count is exactly 1 is the answer.
    Upgrade path: a HashSet gives O(n) time with O(n) space, and XOR of all values gives O(n) time with O(1) space
    because x ^ x = 0 cancels every pair.

Worked Example:
Example:
    nums = [4, 1, 2, 1, 2]
    4 appears once, so the answer is 4.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P17_SingleNumberBruteForce {
    public static int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count == 1) {
                return nums[i];
            }
        }
        throw new IllegalArgumentException("No single number exists");
    }

    public static int singleNumberXor(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("Input      : " + Arrays.toString(nums));
        System.out.println("Brute force: " + singleNumber(nums));
        System.out.println("XOR upgrade: " + singleNumberXor(nums));
        System.out.println("Single item: " + singleNumber(new int[]{7}));
    }
}
