import java.util.*;

/*
Problem: Regular Expression Matching
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Given an input string s and a pattern p, implement regular expression matching with support for:
    '.'  which matches any single character
    '*'  which matches zero or more of the element immediately before it

    The match must cover the entire input string, not just part of it.

Why this pattern fits:
A '*' creates a choice: use zero copies of its element, or consume one character and stay on the same '*'.
    Trying both recursively repeats the same (position in s, position in p) pairs many times.
    Tabulating over those pairs makes the solution polynomial.

Approach:
State: dp[i][j] is true if the first i characters of s match the first j characters of p.
    Base: dp[0][0] is true. An empty string can still match patterns like "a*b*", so fill row 0 using the zero-copy rule.
    Transition when p[j - 1] is not '*': the characters must match (or the pattern has '.') and dp[i - 1][j - 1] must hold.
    Transition when p[j - 1] is '*': either skip the starred element (dp[i][j - 2]),
    or, if s[i - 1] matches the starred element, consume it and stay (dp[i - 1][j]).

Worked Example:
Example:
    s = "aab", p = "c*a*b"
    "c*" matches zero c's, "a*" matches "aa", and "b" matches "b". Answer = true.

    s = "aa", p = "a" is false because the pattern covers only one character.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P42_RegularExpressionMatchingDP {
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char patternChar = p.charAt(j - 1);

                if (patternChar == '*') {
                    char starred = p.charAt(j - 2);
                    boolean zeroCopies = dp[i][j - 2];
                    boolean oneMore = (starred == '.' || starred == s.charAt(i - 1)) && dp[i - 1][j];
                    dp[i][j] = zeroCopies || oneMore;
                } else if (patternChar == '.' || patternChar == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    private static void runDemo(String s, String p) {
        System.out.println("s = \"" + s + "\", p = \"" + p + "\" -> " + isMatch(s, p));
    }

    public static void main(String[] args) {
        runDemo("aa", "a");
        runDemo("aa", "a*");
        runDemo("ab", ".*");
        runDemo("aab", "c*a*b");
        runDemo("mississippi", "mis*is*p*.");
    }
}
