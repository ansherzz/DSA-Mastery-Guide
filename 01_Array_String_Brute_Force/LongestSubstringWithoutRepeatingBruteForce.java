import java.util.*;

/*
Problem: Longest Substring Without Repeating Characters with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a string s, return the length of the longest substring that contains no repeated character.

    Solve it with the direct baseline first. The sliding-window version lives in folder 04.

Why this pattern fits:
The answer is a substring, so every start index is a candidate.
    From each start you can simply extend until a repeat appears. Seeing the repeated work here
    is what motivates the sliding window.

Approach:
For every start index, extend the end index while tracking which characters were already seen.
    Stop extending as soon as a character repeats, because longer substrings from this start are also invalid.
    Record the best length reached.

Worked Example:
Example:
    s = "abcabcbb"
    From start 0 we can extend through "abc" and then hit a second 'a'.
    No start does better, so the answer is 3.

Time Complexity: O(n * min(n, alphabet size))
Space Complexity: O(alphabet size)
*/

public class LongestSubstringWithoutRepeatingBruteForce {
    public static int lengthOfLongestSubstring(String s) {
        int best = 0;

        for (int start = 0; start < s.length(); start++) {
            boolean[] seen = new boolean[65536];
            for (int end = start; end < s.length(); end++) {
                char ch = s.charAt(end);
                if (seen[ch]) {
                    break;
                }
                seen[ch] = true;
                best = Math.max(best, end - start + 1);
            }
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
