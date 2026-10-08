import java.util.*;

/*
Problem: Find Pivot Index
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given an integer array, return the leftmost pivot index where the sum of elements on the left equals
    the sum of elements on the right. If no such index exists, return -1.

    This is a compact interview problem for practicing prefix reasoning.

Why this pattern fits:
Once you know the total sum, the right-side sum can be derived from the left-side sum and the current value.
    That is exactly the kind of mental shortcut prefix-sum problems reward.

Approach:
Compute the total sum.
    Scan from left to right while maintaining leftSum.
    At index i, rightSum = total - leftSum - nums[i]. Compare leftSum and rightSum.

Worked Example:
Example:
    nums = [1, 7, 3, 6, 5, 6]
    The pivot index is 3 because left sum and right sum are both 11.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P03_FindPivotIndexPrefixSum {
    public static int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = total - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums  : " + Arrays.toString(nums));
        System.out.println("Pivot : " + pivotIndex(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 7, 3, 6, 5, 6});
        runDemo(new int[]{1, 2, 3});
    }
}
