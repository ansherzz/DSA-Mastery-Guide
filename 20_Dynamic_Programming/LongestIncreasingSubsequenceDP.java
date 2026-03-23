import java.util.*;

/*
Problem: Longest Increasing Subsequence
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given an integer array, return the length of the longest strictly increasing subsequence.

    This file uses the O(n^2) dynamic programming version because it is excellent for interview understanding.

Why this pattern fits:
For each ending index i, ask:
    what is the best increasing subsequence that ends exactly at i?
    Then extend from every earlier j < i with nums[j] < nums[i].

Approach:
Let dp[i] be the LIS length ending at index i.
    Initialize every dp[i] to 1, then update using earlier compatible positions.

Worked Example:
Example:
    nums = [10,9,2,5,3,7,101,18]
    The answer is 4 for the subsequence [2,3,7,101].

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class LongestIncreasingSubsequenceDP {
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int best = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }

        return best;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
    }
}
