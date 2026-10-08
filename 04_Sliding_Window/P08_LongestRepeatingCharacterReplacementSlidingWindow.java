import java.util.*;

/*
Problem: Longest Repeating Character Replacement
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string of uppercase English letters and an integer k, you may replace at most k characters.
    Return the length of the longest substring that can be turned into a string of all one repeated character.

    The window is valid if the number of non-majority characters is at most k.

Why this pattern fits:
This problem trains a very important window invariant:
    window size - highest frequency in the window <= k.
    That tells you exactly when to shrink.

Approach:
Expand the right end and track character frequencies plus the highest frequency in the current window.
    If windowSize - maxFrequency > k, shrink from the left.

Worked Example:
Example:
    s = "AABABBA", k = 1
    The answer is 4 because "AABA" can become "AAAA" with one replacement.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P08_LongestRepeatingCharacterReplacementSlidingWindow {
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            count[index]++;
            maxFreq = Math.max(maxFreq, count[index]);

            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    private static void runDemo(String s, int k) {
        System.out.println("s = " + s + ", k = " + k);
        System.out.println("Best length: " + characterReplacement(s, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("ABAB", 2);
        runDemo("AABABBA", 1);
    }
}
