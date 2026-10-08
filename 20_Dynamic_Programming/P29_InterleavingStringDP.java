import java.util.*;

/*
Problem: Interleaving String
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given strings s1, s2, and s3, return true if s3 is formed by interleaving s1 and s2
(keeping the letters of each in their original order).

Why this pattern fits:
At every position of s3 the next letter must come from s1 or from s2.
The state is how many letters of each have been consumed; the position in s3 is implied by their sum.

Approach:
dp[i][j] is true if (dp[i - 1][j] and s1[i - 1] == s3[i + j - 1]) or (dp[i][j - 1] and s2[j - 1] == s3[i + j - 1]).
The lengths must add up, and dp[0][0] is true.

Worked Example:
s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" -> aa + dbbc + bc + a + c interleaves correctly. Answer = true.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P29_InterleavingStringDP {
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0 && dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = true;
                }
                if (j > 0 && dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("aabcc, dbbca, aadbbcbcac -> " + isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println("aabcc, dbbca, aadbbbaccc -> " + isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println("three empty strings -> " + isInterleave("", "", ""));
    }
}
