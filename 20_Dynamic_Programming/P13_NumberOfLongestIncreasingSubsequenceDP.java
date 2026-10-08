import java.util.*;

/*
Problem: Number of Longest Increasing Subsequence
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Return the number of strictly increasing subsequences of nums that have the maximum possible length.

Why this pattern fits:
The O(n^2) LIS recurrence extends naturally to counting: whenever several predecessors give the same best length,
their counts add up.

Approach:
length[i] and count[i] start at 1. For every j < i with nums[j] < nums[i]:
if length[j] + 1 > length[i], copy the count; if equal, add the count.
Sum count[i] over all indices whose length equals the global maximum.

Worked Example:
nums = [1, 3, 5, 4, 7] -> [1, 3, 5, 7] and [1, 3, 4, 7] both have length 4. Answer = 2.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class P13_NumberOfLongestIncreasingSubsequenceDP {
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] length = new int[n];
        int[] count = new int[n];
        int longest = 0;
        int total = 0;
        for (int i = 0; i < n; i++) {
            length[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    } else if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }
                }
            }
            if (length[i] > longest) {
                longest = length[i];
                total = count[i];
            } else if (length[i] == longest) {
                total += count[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 5, 4, 7] -> " + findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println("[2, 2, 2, 2, 2] -> " + findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }
}
