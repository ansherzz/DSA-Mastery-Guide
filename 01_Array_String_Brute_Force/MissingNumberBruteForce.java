import java.util.*;

/*
Problem: Missing Number with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an array containing n distinct numbers taken from the range [0, n],
    return the only number in that range that is missing from the array.

Why this pattern fits:
The definition of the answer is itself an algorithm: for each candidate in 0..n, check whether it appears.
    Once that baseline is clear, the sum formula and XOR tricks become easy optimizations.

Approach:
For every candidate value from 0 to n, scan the whole array looking for it.
    The first candidate that is not found is the answer.

Worked Example:
Example:
    nums = [3, 0, 1]
    n = 3, so the candidates are 0, 1, 2, 3.
    0 is present, 1 is present, 2 is not present, so the answer is 2.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class MissingNumberBruteForce {
    public static int missingNumber(int[] nums) {
        int n = nums.length;

        for (int candidate = 0; candidate <= n; candidate++) {
            boolean found = false;
            for (int value : nums) {
                if (value == candidate) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return candidate;
            }
        }
        return -1;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array   : " + Arrays.toString(nums));
        System.out.println("Missing : " + missingNumber(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 0, 1});
        runDemo(new int[]{0, 1});
        runDemo(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
    }
}
