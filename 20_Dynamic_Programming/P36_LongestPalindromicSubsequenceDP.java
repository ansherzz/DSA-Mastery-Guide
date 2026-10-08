import java.util.*;

/*
Problem: Longest Palindromic Subsequence
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given a string s, find the length of the longest palindromic subsequence in s.
    A subsequence is a sequence that can be derived from another sequence by deleting some or no elements
    without changing the order of the remaining elements.

Why this pattern fits:
A palindrome is defined from its two ends inward, so the natural subproblem is a substring s[i..j].
    The answer for a substring depends only on answers for shorter substrings inside it, which is interval DP.

Approach:
State: dp[i][j] is the length of the longest palindromic subsequence within s[i..j].
    Base case: dp[i][i] = 1.
    Transition: if s[i] == s[j], both ends can wrap the best inner palindrome: dp[i][j] = dp[i + 1][j - 1] + 2.
    Otherwise at least one end is unused: dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]).
    Fill i from the end of the string toward the start so that row i + 1 is ready when row i needs it.

Worked Example:
Example:
    s = "bbbab"
    The longest palindromic subsequence is "bbbb" with length 4.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P36_LongestPalindromicSubsequenceDP {
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromeSubseq("a"));
    }
}
