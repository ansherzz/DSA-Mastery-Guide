import java.util.*;

/*
Problem: Count Partitions with Even Sum Difference
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
A partition splits nums at index i (0 <= i < n - 1) into a non-empty left part and a non-empty right part.
Return the number of partitions where the difference between the left sum and the right sum is even.

Why this pattern fits:
Every split needs "sum of the left side" and "sum of the right side".
One running prefix plus the total gives both in O(1) per split.

Approach:
Compute the total. Move i from 0 to n - 2, adding nums[i] to left.
The right sum is total - left; count the split when (left - right) is even.

Worked Example:
nums = [10, 10, 3, 7, 6] -> the differences are -16, 4, 10, 24, all even. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P06_CountPartitionsWithEvenSumDifferencePrefixSum {
    public static int countPartitions(int[] nums) {
        int total = 0;
        for (int value : nums) {
            total += value;
        }
        int left = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            int right = total - left;
            if ((left - right) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[10, 10, 3, 7, 6] -> " + countPartitions(new int[]{10, 10, 3, 7, 6}));
        System.out.println("[1, 2, 2] -> " + countPartitions(new int[]{1, 2, 2}));
        System.out.println("[2, 4, 6, 8] -> " + countPartitions(new int[]{2, 4, 6, 8}));
    }
}
