import java.util.*;

/*
Problem: Permutation in String
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given two strings s1 and s2, return true if some substring of s2 is a permutation of s1.
    In other words, return true if s2 contains any contiguous block with exactly the same character counts as s1.

    This is a fixed-size sliding-window frequency problem.

Why this pattern fits:
The window size is forced to be s1.length(). That makes the problem about maintaining character counts
    as the window slides one step at a time.

Approach:
Count letters from s1 and the current window in s2.
    Slide a fixed-size window across s2, updating counts as one character enters and one leaves.
    Compare the counts at each step.

Worked Example:
Example:
    s1 = "ab", s2 = "eidbaooo"
    The substring "ba" is a permutation of "ab", so the answer is true.

Time Complexity: O(n * alphabet) with direct array comparison, effectively O(n)
Space Complexity: O(1)
*/

public class P13_PermutationInStringSlidingWindow {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : s1.toCharArray()) {
            need[c - 'a']++;
        }

        int size = s1.length();
        for (int i = 0; i < s2.length(); i++) {
            window[s2.charAt(i) - 'a']++;

            if (i >= size) {
                window[s2.charAt(i - size) - 'a']--;
            }

            if (Arrays.equals(need, window)) {
                return true;
            }
        }

        return false;
    }

    private static void runDemo(String s1, String s2) {
        System.out.println("s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Contains permutation? " + checkInclusion(s1, s2));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("ab", "eidbaooo");
        runDemo("ab", "eidboaoo");
    }
}
