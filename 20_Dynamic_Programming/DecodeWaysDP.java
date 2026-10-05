import java.util.*;

/*
Problem: Decode Ways
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
A message of uppercase letters is encoded with the mapping 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".
    Given a string of digits, return the number of ways it can be decoded.
    A code may not have a leading zero, so "06" is not a valid encoding of 'F'.

Why this pattern fits:
The first decision is whether to decode one digit or two digits as the next letter.
    After that decision, the rest of the string is the same problem on a shorter suffix,
    and many different decision sequences lead to the same suffix. That is overlapping subproblems.

Approach:
State: ways[i] is the number of ways to decode the first i characters. ways[0] = 1 for the empty prefix.
    Transition: if the single digit s[i - 1] is not '0', add ways[i - 1].
    If the two digits s[i - 2..i - 1] form a number between 10 and 26, add ways[i - 2].
    Only the previous two values are needed, so two variables suffice.

Worked Example:
Example:
    s = "226"
    Decodings: "2 2 6" (BBF), "22 6" (VF), and "2 26" (BZ). Answer = 3.

    s = "06" has no valid decoding because "0" cannot stand alone and "06" has a leading zero.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class DecodeWaysDP {
    public static int numDecodings(String s) {
        int twoBack = 1;
        int oneBack = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= s.length(); i++) {
            int current = 0;

            if (s.charAt(i - 1) != '0') {
                current += oneBack;
            }

            int twoDigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twoDigits >= 10 && twoDigits <= 26) {
                current += twoBack;
            }

            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    private static void runDemo(String s) {
        System.out.println("\"" + s + "\" -> " + numDecodings(s));
    }

    public static void main(String[] args) {
        runDemo("12");
        runDemo("226");
        runDemo("06");
        runDemo("2101");
    }
}
