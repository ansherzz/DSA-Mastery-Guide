import java.util.*;

/*
Problem: Valid Palindrome II
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a string s, return true if s can become a palindrome after deleting at most one character.

Why this pattern fits:
A palindrome check walks inward from both ends. The first mismatch is the only place where a decision is needed:
    either the left character or the right character must be the one that gets deleted.

Approach:
Move two pointers inward while the characters match.
    At the first mismatch, try skipping the left character and try skipping the right character.
    The string is valid if either remaining range is a plain palindrome.

Worked Example:
Example:
    s = "abca"
    'a' matches 'a'. Then 'b' and 'c' mismatch.
    Skipping 'b' leaves "c" and skipping 'c' leaves "b"; both are palindromes. Answer = true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P10_ValidPalindromeIITwoPointers {
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(String s) {
        System.out.println("Input : " + s);
        System.out.println("Valid?: " + validPalindrome(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("aba");
        runDemo("abca");
        runDemo("abc");
    }
}
