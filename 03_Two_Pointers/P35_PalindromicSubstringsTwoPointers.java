import java.util.*;

/*
Problem: Palindromic Substrings
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given a string s, return the number of substrings that are palindromes.
Substrings at different positions count separately.

Why this pattern fits:
A palindrome is defined by its center. Starting two pointers at the center and moving them apart
visits every palindrome exactly once without re-checking interiors.

Approach:
For every index, expand from (i, i) for odd lengths and (i, i + 1) for even lengths.
Each successful expansion step is one more palindromic substring.

Worked Example:
s = "aaa" -> "a" three times, "aa" twice, "aaa" once. Answer = 6.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P35_PalindromicSubstringsTwoPointers {
    private static int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static int countSubstrings(String s) {
        int total = 0;
        for (int center = 0; center < s.length(); center++) {
            total += expand(s, center, center);
            total += expand(s, center, center + 1);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("abc -> " + countSubstrings("abc"));
        System.out.println("aaa -> " + countSubstrings("aaa"));
    }
}
