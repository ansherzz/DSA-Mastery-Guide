import java.util.*;

/*
Problem: Max Consecutive Ones
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given a binary array nums, return the maximum number of consecutive 1s in the array.

Why this pattern fits:
This is the simplest "longest run" problem. One pass with a running counter is enough,
    and the same running-counter idea is the seed of the sliding window pattern.

Approach:
Keep the length of the current run of 1s.
    A 1 extends the run; a 0 resets it to zero.
    After each element, update the best run length seen so far.

Worked Example:
Example:
    nums = [1, 1, 0, 1, 1, 1]
    Runs of 1s have lengths 2 and 3, so the answer is 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MaxConsecutiveOnesArrayBasics {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int best = 0;
        int current = 0;

        for (int num : nums) {
            if (num == 1) {
                current++;
                best = Math.max(best, current);
            } else {
                current = 0;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{0, 0}));
    }
}
