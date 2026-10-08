import java.util.*;

/*
Problem: Maximum Number of Vowels in a Substring of Given Length
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string s of lowercase English letters and an integer k,
    return the maximum number of vowel letters in any substring of s with length k.
    The vowels are a, e, i, o, u.

Why this pattern fits:
Every candidate substring has the same length k, so this is a fixed-size window.
    The vowel count changes by at most one on each side as the window slides.

Approach:
Count vowels in the first k characters.
    For each later position, add one if the entering character is a vowel and subtract one if the leaving character is a vowel.
    Keep the maximum count. Stop early if the count reaches k, because it cannot go higher.

Worked Example:
Example:
    s = "abciiidef", k = 3
    The window "iii" contains 3 vowels, which is the maximum possible.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P22_MaximumVowelsInSubstringSlidingWindow {
    public static int maxVowels(String s, int k) {
        int vowels = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            if (isVowel(s.charAt(right))) {
                vowels++;
            }
            if (right >= k && isVowel(s.charAt(right - k))) {
                vowels--;
            }
            best = Math.max(best, vowels);
            if (best == k) {
                break;
            }
        }
        return best;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    private static void runDemo(String s, int k) {
        System.out.println("s = " + s + ", k = " + k);
        System.out.println("Max vowels: " + maxVowels(s, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("abciiidef", 3);
        runDemo("aeiou", 2);
        runDemo("leetcode", 3);
    }
}
