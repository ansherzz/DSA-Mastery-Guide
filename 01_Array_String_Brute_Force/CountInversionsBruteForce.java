import java.util.*;

/*
Problem: Count Inversions with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an integer array, count the number of inversions.
    An inversion is a pair of indices (i, j) with i < j and nums[i] > nums[j].
    The inversion count measures how far the array is from being sorted.

Why this pattern fits:
The definition is a statement about pairs, so checking every pair is the obvious correct baseline.
    It is also the reference solution you would use to test the faster merge-sort based version.

Approach:
Use two nested loops with i < j.
    Every time the earlier value is strictly greater than the later value, count one inversion.
    Use a long for the count because the number of pairs grows quadratically.

Worked Example:
Example:
    nums = [2, 4, 1, 3, 5]
    Inversions are (2, 1), (4, 1), and (4, 3), so the answer is 3.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class CountInversionsBruteForce {
    public static long countInversions(int[] nums) {
        long count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array      : " + Arrays.toString(nums));
        System.out.println("Inversions : " + countInversions(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 4, 1, 3, 5});
        runDemo(new int[]{5, 4, 3, 2, 1});
        runDemo(new int[]{1, 2, 3});
    }
}
