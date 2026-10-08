import java.util.*;

/*
Problem: Minimum Window Substring
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
Given strings s and t, return the smallest substring of s that contains every character of t,
    including multiplicities. If no such substring exists, return an empty string.

    This is the classic variable-size sliding-window counting problem.

Why this pattern fits:
The window grows until it becomes valid, then shrinks to become minimal while staying valid.
    This repeated expand-then-contract behavior is the signature of many hard window problems.

Approach:
Count the characters required by t.
    Expand right and track how many required characters have been satisfied.
    Once the window is valid, shrink from the left to find the minimum.

Worked Example:
Example:
    s = "ADOBECODEBANC", t = "ABC"
    The smallest valid window is "BANC".

Time Complexity: O(|s| + |t|)
Space Complexity: O(alphabet)
*/

public class P27_MinimumWindowSubstringSlidingWindow {
    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                if (right - left + 1 < bestLength) {
                    bestLength = right - left + 1;
                    bestStart = left;
                }

                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);
                if (need.containsKey(remove) && window.get(remove) < need.get(remove)) {
                    formed--;
                }
                left++;
            }
        }

        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLength);
    }

    private static void runDemo(String s, String t) {
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Window: " + minWindow(s, t));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("ADOBECODEBANC", "ABC");
        runDemo("a", "aa");
    }
}
