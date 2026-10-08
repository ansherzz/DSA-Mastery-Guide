import java.util.*;

/*
Problem: Minimum Average Difference
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
The average difference of index i is the absolute difference between the average of the first i + 1 elements
and the average of the last n - i - 1 elements (integer division; an empty side averages to 0).
Return the index with the minimum average difference, preferring the smallest index.

Why this pattern fits:
Each index needs a prefix sum and a suffix sum; total - prefix supplies the suffix.

Approach:
Keep a running prefix sum in a long. For each i compute both averages,
guarding the empty right side, and track the smallest difference.

Worked Example:
nums = [2, 5, 3, 9, 5, 3] -> at index 3: |19 / 4 - 8 / 2| = |4 - 4| = 0. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P23_MinimumAverageDifferencePrefixSum {
    public static int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long total = 0;
        for (int value : nums) {
            total += value;
        }
        long prefix = 0;
        long best = Long.MAX_VALUE;
        int bestIndex = 0;
        for (int i = 0; i < n; i++) {
            prefix += nums[i];
            long leftAverage = prefix / (i + 1);
            long rightAverage = i == n - 1 ? 0 : (total - prefix) / (n - i - 1);
            long difference = Math.abs(leftAverage - rightAverage);
            if (difference < best) {
                best = difference;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    public static void main(String[] args) {
        System.out.println("[2, 5, 3, 9, 5, 3] -> " + minimumAverageDifference(new int[]{2, 5, 3, 9, 5, 3}));
        System.out.println("[0] -> " + minimumAverageDifference(new int[]{0}));
    }
}
