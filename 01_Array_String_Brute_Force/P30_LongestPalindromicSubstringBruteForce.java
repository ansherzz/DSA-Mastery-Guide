import java.util.*;

/*
Problem: Longest Palindromic Substring with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a string s, return the longest substring of s that reads the same forward and backward.
    If several substrings share the maximum length, return the one that starts first.

Why this pattern fits:
The answer is some substring, and a substring is fully described by a start and an end.
    Enumerating every (start, end) pair and testing it is the correctness-first baseline.

Approach:
Try every start index and every end index at or after it.
    Skip candidates that cannot beat the current best length.
    Check each remaining candidate with a simple inward-moving palindrome test.

Worked Example:
Example:
    s = "babad"
    Candidates include "bab" (palindrome, length 3) and "aba" (palindrome, length 3).
    "bab" starts first, so the answer is "bab".

Time Complexity: O(n^3)
Space Complexity: O(1) extra
*/

public class P30_LongestPalindromicSubstringBruteForce {
    public static String longestPalindrome(String s) {
        int bestStart = 0;
        int bestLength = 0;

        for (int start = 0; start < s.length(); start++) {
            for (int end = start; end < s.length(); end++) {
                int length = end - start + 1;
                if (length > bestLength && isPalindrome(s, start, end)) {
                    bestStart = start;
                    bestLength = length;
                }
            }
        }
        return s.substring(bestStart, bestStart + bestLength);
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static void runDemo(String s) {
        System.out.println("String : " + s);
        System.out.println("Longest: " + longestPalindrome(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("babad");
        runDemo("cbbd");
        runDemo("forgeeksskeegfor");
    }
}
