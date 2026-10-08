import java.util.*;

/*
Problem: Distinct Subsequences
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Given two strings s and t, return the number of distinct subsequences of s which equal t.
    Two subsequences are distinct if they use different sets of positions of s, even if the characters are the same.

Why this pattern fits:
Each character of s is either used to match the next needed character of t or skipped.
    The number of ways depends only on how much of s has been read and how much of t has been matched,
    so prefix pairs define the subproblems.

Approach:
State: dp[j] is the number of ways to form the first j characters of t using the characters of s read so far.
    dp[0] = 1, because the empty string is formed in exactly one way.
    For each character of s, sweep j from the end of t down to 1:
    if the character equals t[j - 1], then dp[j] += dp[j - 1] (use this character as the j-th match).
    Skipping the character is the value dp[j] already holds.
    The backward sweep prevents one character of s from being used for two positions of t.

Worked Example:
Example:
    s = "rabbbit", t = "rabbit"
    Any one of the three b's in s can be the one left out, giving 3 distinct subsequences.

Time Complexity: O(n * m)
Space Complexity: O(m)
*/

public class DistinctSubsequencesDP {
    public static int numDistinct(String s, String t) {
        int m = t.length();
        long[] dp = new long[m + 1];
        dp[0] = 1;

        for (char ch : s.toCharArray()) {
            for (int j = m; j >= 1; j--) {
                if (ch == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return (int) dp[m];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
        System.out.println(numDistinct("abc", "abcd"));
    }
}
