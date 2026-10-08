import java.util.*;

/*
Problem: String to Integer (atoi)
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Implement atoi: convert a string to a 32-bit signed integer.
1. Skip leading spaces. 2. Read an optional '+' or '-'. 3. Read digits until a non-digit or the end.
If no digits were read return 0. Clamp the result to [-2^31, 2^31 - 1].

Why this pattern fits:
The difficulty is not the algorithm but the careful state-by-state parsing and overflow handling.

Approach:
Advance an index through the three phases.
Accumulate the value in a long and clamp as soon as it leaves the int range, so the long itself never overflows.

Worked Example:
s = "   -42"  -> skip spaces, sign = -1, digits 42 -> -42.
s = "-91283472332" -> exceeds the range, so it clamps to -2147483648.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P28_StringToIntegerAtoiArrayBasics {
    public static int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }
        long value = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            value = value * 10 + (s.charAt(i) - '0');
            if (sign * value > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * value < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int) (sign * value);
    }

    public static void main(String[] args) {
        for (String s : new String[]{"42", "   -42", "4193 with words", "-91283472332", "words and 987"}) {
            System.out.println("\"" + s + "\" -> " + myAtoi(s));
        }
    }
}
