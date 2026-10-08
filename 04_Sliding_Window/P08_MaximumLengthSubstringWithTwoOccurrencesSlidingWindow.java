import java.util.*;

/*
Problem: Maximum Length Substring With Two Occurrences
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
Given a string s, return the maximum length of a substring in which every character appears at most twice.

Why this pattern fits:
The constraint is monotonic: if a window is valid, every smaller window inside it is valid.
That is the signal for a grow-and-shrink variable window.

Approach:
Add s[right] to the counts. While that letter's count exceeds 2, remove s[left] and advance left.
Record the window length after each step.

Worked Example:
s = "bcbbbcba" -> the window "bcbb" (and "bbcb", "bcba") has length 4. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P08_MaximumLengthSubstringWithTwoOccurrencesSlidingWindow {
    public static int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int letter = s.charAt(right) - 'a';
            count[letter]++;
            while (count[letter] > 2) {
                count[s.charAt(left++) - 'a']--;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("bcbbbcba -> " + maximumLengthSubstring("bcbbbcba"));
        System.out.println("aaaa -> " + maximumLengthSubstring("aaaa"));
    }
}
