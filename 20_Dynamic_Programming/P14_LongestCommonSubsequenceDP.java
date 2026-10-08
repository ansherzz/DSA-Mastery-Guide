import java.util.*;

/*
Problem: Longest Common Subsequence
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given two strings, return the length of their longest common subsequence.
    A subsequence keeps relative order but does not need to be contiguous.

    This is a foundational 2D dynamic programming problem.

Why this pattern fits:
The answer depends on prefixes of both strings.
    That naturally creates a grid of overlapping subproblems.

Approach:
Let dp[i][j] be the LCS length for the first i characters of text1 and the first j characters of text2.
    If the current characters match, extend the diagonal.
    Otherwise take the best of removing one character from either side.

Worked Example:
Example:
    text1 = "abcde", text2 = "ace"
    The answer is 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P14_LongestCommonSubsequenceDP {
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
        System.out.println(longestCommonSubsequence("abc", "abc"));
        System.out.println(longestCommonSubsequence("abc", "def"));
    }
}
