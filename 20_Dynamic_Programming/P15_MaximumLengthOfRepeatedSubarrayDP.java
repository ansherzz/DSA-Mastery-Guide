import java.util.*;

/*
Problem: Maximum Length of Repeated Subarray
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given two integer arrays, return the maximum length of a subarray that appears in both
(the longest common substring problem on arrays).

Why this pattern fits:
It looks like LCS, but the match must be contiguous. So the state is "common suffix ending exactly here",
and unlike LCS a mismatch does not inherit from neighbors - it breaks the run.

Approach:
If nums1[i - 1] == nums2[j - 1] then dp[i][j] = dp[i - 1][j - 1] + 1, otherwise 0. Track the maximum cell.

Worked Example:
nums1 = [1, 2, 3, 2, 1], nums2 = [3, 2, 1, 4, 7] -> the shared subarray [3, 2, 1] has length 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P15_MaximumLengthOfRepeatedSubarrayDP {
    public static int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int best = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    best = Math.max(best, dp[i][j]);
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 2, 1] / [3, 2, 1, 4, 7] -> " + findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
        System.out.println("five zeros twice -> " + findLength(new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}));
    }
}
