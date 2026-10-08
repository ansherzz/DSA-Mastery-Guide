import java.util.*;

/*
Problem: Maximum Score After Splitting a String
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given a string s of zeros and ones, return the maximum score after splitting the string
    into two non-empty substrings (a left substring and a right substring).
    The score is the number of zeros in the left substring plus the number of ones in the right substring.

Why this pattern fits:
Every split point needs two counts: zeros before it and ones after it.
    Both are running totals, so each split can be scored in O(1) after one counting pass.

Approach:
Count the total number of ones. Then scan the split positions from left to right.
    Keep zerosLeft, a prefix count of zeros, and onesRight, the ones not yet passed.
    The split must leave at least one character on the right, so stop before the last index.

Worked Example:
Example:
    s = "011101"
    Split "0" | "11101": 1 zero on the left + 4 ones on the right = 5, which is the maximum.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P08_MaximumScoreAfterSplittingStringPrefixSum {
    public static int maxScore(String s) {
        int onesRight = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                onesRight++;
            }
        }

        int zerosLeft = 0;
        int best = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zerosLeft++;
            } else {
                onesRight--;
            }
            best = Math.max(best, zerosLeft + onesRight);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maxScore("011101"));
        System.out.println(maxScore("00111"));
        System.out.println(maxScore("1111"));
    }
}
