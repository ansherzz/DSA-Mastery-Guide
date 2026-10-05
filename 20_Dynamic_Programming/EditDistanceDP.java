import java.util.*;

/*
Problem: Edit Distance
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given two strings word1 and word2, return the minimum number of operations required to convert word1 into word2.
    Three operations are allowed, each on a single character: insert, delete, or replace.

Why this pattern fits:
Compare the last characters of the two prefixes. If they match, nothing is spent on them.
    If they differ, one of three operations must happen, and each one leaves a smaller pair of prefixes.
    The same prefix pairs are reached through many different operation sequences, so their answers are stored in a table.

Approach:
State: dp[i][j] is the edit distance between the first i characters of word1 and the first j characters of word2.
    Base: dp[i][0] = i (delete everything) and dp[0][j] = j (insert everything).
    Transition: if the characters match, dp[i][j] = dp[i - 1][j - 1].
    Otherwise dp[i][j] = 1 + min(dp[i - 1][j] for a delete, dp[i][j - 1] for an insert, dp[i - 1][j - 1] for a replace).

Worked Example:
Example:
    word1 = "horse", word2 = "ros"
    horse -> rorse (replace h with r) -> rose (delete r) -> ros (delete e). Answer = 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class EditDistanceDP {
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int delete = dp[i - 1][j];
                    int insert = dp[i][j - 1];
                    int replace = dp[i - 1][j - 1];
                    dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
                }
            }
        }
        return dp[m][n];
    }

    private static void runDemo(String word1, String word2) {
        System.out.println(word1 + " -> " + word2 + " : " + minDistance(word1, word2));
    }

    public static void main(String[] args) {
        runDemo("horse", "ros");
        runDemo("intention", "execution");
        runDemo("", "abc");
    }
}
