import java.util.*;

/*
Problem: Longest Substring with At Most K Distinct Characters
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a string s and an integer k, return the length of the longest substring of s
    that contains at most k distinct characters.

Why this pattern fits:
"Longest contiguous range that satisfies a limit" is the signature of a variable-size window.
    The number of distinct characters only grows when the window expands and only shrinks when it contracts.

Approach:
Expand the right pointer and record character counts in a map.
    While the map has more than k keys, move the left pointer and drop characters whose count reaches zero.
    The window is valid after shrinking, so compare its length with the best so far.

Worked Example:
Example:
    s = "eceba", k = 2
    The substring "ece" has two distinct characters and length 3. Any longer substring has three.

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class P19_LongestSubstringKDistinctSlidingWindow {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }

        Map<Character, Integer> count = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            count.merge(s.charAt(right), 1, Integer::sum);

            while (count.size() > k) {
                char leaving = s.charAt(left++);
                if (count.merge(leaving, -1, Integer::sum) == 0) {
                    count.remove(leaving);
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static void runDemo(String s, int k) {
        System.out.println("s = " + s + ", k = " + k);
        System.out.println("Best length: " + lengthOfLongestSubstringKDistinct(s, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("eceba", 2);
        runDemo("aa", 1);
        runDemo("aabacbebebe", 3);
    }
}
