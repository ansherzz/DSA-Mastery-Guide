import java.util.*;

/*
Problem: Repeated Substring Pattern
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given a string s, return true if it can be built by taking a substring of it
and appending multiple copies of that substring together.

Why this pattern fits:
The repeating unit must be a prefix whose length divides n.
There are few such lengths, so trying each one directly is simple and fast enough.

Approach:
For every length len from 1 to n / 2 that divides n,
check that s[i] == s[i - len] for every i >= len. If any length passes, return true.

Worked Example:
s = "abcabcabcabc"
len = 3 divides 12 and every character equals the one 3 positions earlier, so the answer is true.

Time Complexity: O(n * number of divisors of n)
Space Complexity: O(1)
*/

public class P23_RepeatedSubstringPatternBruteForce {
    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int len = 1; len <= n / 2; len++) {
            if (n % len != 0) {
                continue;
            }
            boolean matches = true;
            for (int i = len; i < n && matches; i++) {
                matches = s.charAt(i) == s.charAt(i - len);
            }
            if (matches) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("abab -> " + repeatedSubstringPattern("abab"));
        System.out.println("aba -> " + repeatedSubstringPattern("aba"));
        System.out.println("abcabcabcabc -> " + repeatedSubstringPattern("abcabcabcabc"));
    }
}
