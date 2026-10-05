import java.util.*;

/*
Problem: Left and Right Sum Differences
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given an integer array nums, build an array answer of the same length where
    answer[i] = |leftSum[i] - rightSum[i]|.
    leftSum[i] is the sum of the elements strictly to the left of index i,
    and rightSum[i] is the sum of the elements strictly to the right of index i.

Why this pattern fits:
leftSum is a prefix sum, and rightSum can be derived from the total without a second array:
    rightSum = total - leftSum - nums[i].

Approach:
Compute the total sum once.
    Walk through the array while maintaining leftSum.
    For each index compute rightSum from the total, store the absolute difference, then add nums[i] to leftSum.

Worked Example:
Example:
    nums = [10, 4, 8, 3]
    leftSum  = [0, 10, 14, 22]
    rightSum = [15, 11, 3, 0]
    answer   = [15, 1, 11, 22]

Time Complexity: O(n)
Space Complexity: O(1) extra, excluding the output
*/

public class LeftAndRightSumDifferencesPrefixSum {
    public static int[] leftRightDifference(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int[] answer = new int[nums.length];
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = total - leftSum - nums[i];
            answer[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }
        return answer;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Answer : " + Arrays.toString(leftRightDifference(nums)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{10, 4, 8, 3});
        runDemo(new int[]{1});
    }
}
