import java.util.*;

/*
Problem: Longest Substring with At Least K Repeating Characters
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given a string s of lowercase letters and an integer k, return the length of the longest substring
    in which every character that appears does so at least k times.
    If no such substring exists, return 0.

Why this pattern fits:
A character that appears fewer than k times in the current range can never be part of a valid substring
    within that range. Such a character is a forced split point: the answer must lie entirely
    on one side of it. Splitting on these characters divides the problem into independent pieces.

Approach:
Count the characters in the current range.
    Find the first character whose count is below k. If there is none, the whole range is valid.
    Otherwise split the range at every occurrence of such "bad" characters and solve each piece recursively,
    returning the best result.

Worked Example:
Example:
    s = "ababbc", k = 2
    'c' appears once, so it splits the string into "ababb" and "".
    In "ababb", a appears 2 times and b appears 3 times, so the whole piece is valid. Answer = 5.

Time Complexity: O(26 * n) in typical cases, O(n^2) in the worst case
Space Complexity: O(n) recursion stack in the worst case
*/

public class LongestSubstringWithAtLeastKRepeatingDivideConquer {
    public static int longestSubstring(String s, int k) {
        return solve(s, 0, s.length(), k);
    }

    private static int solve(String s, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }

        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }

        boolean allValid = true;
        for (int i = start; i < end; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                allValid = false;
                break;
            }
        }
        if (allValid) {
            return end - start;
        }

        int best = 0;
        int pieceStart = start;
        for (int i = start; i <= end; i++) {
            if (i == end || count[s.charAt(i) - 'a'] < k) {
                best = Math.max(best, solve(s, pieceStart, i, k));
                pieceStart = i + 1;
            }
        }
        return best;
    }

    private static void runDemo(String s, int k) {
        System.out.println("s = " + s + ", k = " + k + " -> " + longestSubstring(s, k));
    }

    public static void main(String[] args) {
        runDemo("aaabb", 3);
        runDemo("ababbc", 2);
        runDemo("abcdef", 2);
    }
}
