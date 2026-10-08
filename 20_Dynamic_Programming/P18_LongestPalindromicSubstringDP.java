import java.util.*;

/*
Problem: Longest Palindromic Substring
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given a string s, return the longest substring of s that is a palindrome.
    If several substrings share the maximum length, return the one that starts first.

    The O(n^3) brute-force version is in folder 01. Solve it here in O(n^2) with dynamic programming.

Why this pattern fits:
A substring is a palindrome exactly when its two end characters match and the substring between them
    is also a palindrome. The inner substring is a smaller instance of the same question,
    so answers for short substrings can be reused to answer longer ones.

Approach:
State: isPalindrome[i][j] is true if s[i..j] is a palindrome.
    Transition: s[i] == s[j] and (the length is at most 2 or isPalindrome[i + 1][j - 1]).
    Fill the table by increasing substring length so the inner substring is always computed first.
    Record the first longest palindrome seen.

Worked Example:
Example:
    s = "babad"
    "bab" is a palindrome because b == b and the inner "a" is a palindrome.
    No longer palindrome exists, and "bab" starts before "aba". Answer = "bab".

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P18_LongestPalindromicSubstringDP {
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }

        boolean[][] isPalindrome = new boolean[n][n];
        int bestStart = 0;
        int bestLength = 1;

        for (int length = 1; length <= n; length++) {
            for (int start = 0; start + length <= n; start++) {
                int end = start + length - 1;
                if (s.charAt(start) != s.charAt(end)) {
                    continue;
                }
                if (length <= 2 || isPalindrome[start + 1][end - 1]) {
                    isPalindrome[start][end] = true;
                    if (length > bestLength) {
                        bestStart = start;
                        bestLength = length;
                    }
                }
            }
        }
        return s.substring(bestStart, bestStart + bestLength);
    }

    private static void runDemo(String s) {
        System.out.println(s + " -> " + longestPalindrome(s));
    }

    public static void main(String[] args) {
        runDemo("babad");
        runDemo("cbbd");
        runDemo("forgeeksskeegfor");
    }
}
