import java.util.*;

/*
Problem: Longest Substring Without Repeating Characters
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string, return the length of the longest substring that contains no repeated characters.

    The substring must be contiguous, which makes this a classic sliding-window problem.

Why this pattern fits:
As the right end expands, duplicates may appear.
    Sliding window lets you shrink the left end just enough to restore validity.

Approach:
Track the most recent index of each character in a hashmap.
    Move the left boundary forward whenever the current character repeats inside the current window.

Worked Example:
Example:
    s = "abcabcbb"
    The longest substring without repetition is "abc", so the answer is 3.

Time Complexity: O(n)
Space Complexity: O(min(n, alphabet))
*/

public class LongestSubstringWithoutRepeatingSlidingWindow {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (lastSeen.containsKey(current)) {
                left = Math.max(left, lastSeen.get(current) + 1);
            }
            lastSeen.put(current, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    private static void runDemo(String s) {
        System.out.println("String: " + s);
        System.out.println("Best  : " + lengthOfLongestSubstring(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("abcabcbb");
        runDemo("bbbbb");
        runDemo("pwwkew");
    }
}
