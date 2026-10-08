import java.util.*;

/*
Problem: Find K-Length Substrings With No Repeated Characters
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string s and an integer k, return the number of substrings of length k with no repeated characters.

Why this pattern fits:
A length-k window ending at `right` is repeat-free exactly when the longest repeat-free window
ending at `right` has length at least k. So one variable window answers every fixed window.

Approach:
Extend right and shrink from the left while the new letter is duplicated.
If the resulting window has length >= k, the last k characters form a valid substring, so count one.

Worked Example:
s = "havefunonleetcode", k = 5 -> "havef", "avefu", "vefun", "efuno", "etcod", "tcode". Answer = 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P18_FindKLengthSubstringsWithNoRepeatedCharactersSlidingWindow {
    public static int numKLenSubstrNoRepeats(String s, int k) {
        int[] count = new int[26];
        int result = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int letter = s.charAt(right) - 'a';
            count[letter]++;
            while (count[letter] > 1) {
                count[s.charAt(left++) - 'a']--;
            }
            if (right - left + 1 >= k) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("havefunonleetcode, k = 5 -> " + numKLenSubstrNoRepeats("havefunonleetcode", 5));
        System.out.println("home, k = 5 -> " + numKLenSubstrNoRepeats("home", 5));
    }
}
