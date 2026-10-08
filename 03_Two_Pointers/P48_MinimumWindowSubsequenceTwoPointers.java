import java.util.*;

/*
Problem: Minimum Window Subsequence
Pattern: 03. Two Pointers
Difficulty: Hard

Interview Prompt:
Given strings s1 and s2, return the minimum contiguous substring of s1 such that s2 is a subsequence of it.
    If there is no such window, return the empty string.
    If there are multiple windows of the minimum length, return the one with the left-most starting index.

Why this pattern fits:
Subsequence matching is a two-pointer walk. Unlike Minimum Window Substring, order matters,
    so a frequency window does not work. Instead, a forward walk finds where a match ends
    and a backward walk tightens where it starts.

Approach:
Forward pass: advance i through s1 and j through s2; when j reaches the end of s2, a window ending at i exists.
    Backward pass: from that end, walk i backward while matching s2 from its last character to its first.
    This finds the latest possible start for this end, which is the shortest window ending here.
    Record the window if it is strictly shorter than the best, then restart the forward pass
    from one position after that start.

Worked Example:
Example:
    s1 = "abcdebdde", s2 = "bde"
    Forward match ends at index 4 ("abcde"); backward tightening gives "bcde" (indices 1..4).
    A later match gives "bdde", which has the same length but starts later. Answer: "bcde".

Time Complexity: O(n * m) in the worst case
Space Complexity: O(1)
*/

public class P48_MinimumWindowSubsequenceTwoPointers {
    public static String minWindow(String s1, String s2) {
        int bestStart = -1;
        int bestLength = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        while (i < s1.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }

            if (j == s2.length()) {
                int end = i;
                j--;
                while (j >= 0) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        j--;
                    }
                    i--;
                }
                i++;

                if (end - i + 1 < bestLength) {
                    bestLength = end - i + 1;
                    bestStart = i;
                }
                j = 0;
            }
            i++;
        }

        return bestStart == -1 ? "" : s1.substring(bestStart, bestStart + bestLength);
    }

    public static void main(String[] args) {
        System.out.println("[" + minWindow("abcdebdde", "bde") + "]");
        System.out.println("[" + minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u") + "]");
        System.out.println("[" + minWindow("cnhczmccqouqadqtmjjzl", "mm") + "]");
    }
}
