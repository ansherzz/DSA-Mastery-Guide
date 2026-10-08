import java.util.*;

/*
Problem: Is Subsequence
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given two strings s and t, return true if s is a subsequence of t.
    A subsequence is formed by deleting zero or more characters from t without changing the order
    of the remaining characters.

Why this pattern fits:
Two sequences are consumed in order, each with its own pointer.
    Matching each character of s as early as possible in t is always safe, so a greedy scan works.

Approach:
Keep one pointer in s and one in t.
    Always advance the t pointer. Advance the s pointer only when the two current characters match.
    If the s pointer reaches the end of s, every character was matched in order.

Worked Example:
Example:
    s = "abc", t = "ahbgdc"
    'a' matches at index 0, 'b' matches at index 2, 'c' matches at index 5. Answer = true.

Time Complexity: O(|t|)
Space Complexity: O(1)
*/

public class P09_IsSubsequenceTwoPointers {
    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; j < t.length() && i < s.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }
        return i == s.length();
    }

    private static void runDemo(String s, String t) {
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Subsequence? " + isSubsequence(s, t));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("abc", "ahbgdc");
        runDemo("axc", "ahbgdc");
        runDemo("", "anything");
    }
}
