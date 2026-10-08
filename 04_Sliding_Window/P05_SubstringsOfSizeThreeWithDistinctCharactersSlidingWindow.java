import java.util.*;

/*
Problem: Substrings of Size Three with Distinct Characters
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
A string is good if it has no repeated characters.
Return the number of good substrings of length three in s.

Why this pattern fits:
The window size is fixed at three, so each window is checked in constant time while sliding by one.

Approach:
For every index i >= 2, compare s[i], s[i - 1], and s[i - 2]. Count the window when all three differ.

Worked Example:
s = "aababcabc" -> good windows are "abc", "bca", "cab", "abc". Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P05_SubstringsOfSizeThreeWithDistinctCharactersSlidingWindow {
    public static int countGoodSubstrings(String s) {
        int count = 0;
        for (int i = 2; i < s.length(); i++) {
            char a = s.charAt(i - 2);
            char b = s.charAt(i - 1);
            char c = s.charAt(i);
            if (a != b && b != c && a != c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("xyzzaz -> " + countGoodSubstrings("xyzzaz"));
        System.out.println("aababcabc -> " + countGoodSubstrings("aababcabc"));
    }
}
