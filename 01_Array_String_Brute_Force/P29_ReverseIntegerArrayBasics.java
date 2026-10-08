import java.util.*;

/*
Problem: Reverse Integer
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a signed 32-bit integer x, return x with its digits reversed.
If the reversed value does not fit in a signed 32-bit integer, return 0.
Assume you may not store 64-bit integers.

Why this pattern fits:
Digit extraction with % and / is a core array-free simulation skill.
The interview twist is detecting overflow before it happens.

Approach:
Repeatedly take digit = x % 10 and x /= 10.
Before computing result * 10 + digit, check that result is within MAX / 10 and MIN / 10.
Java's % keeps the sign, so negative numbers work with the same loop.

Worked Example:
x = -123 -> digits popped are -3, -2, -1 -> result -321.
x = 1534236469 -> the reversed value 9646324351 overflows, so return 0.

Time Complexity: O(number of digits)
Space Complexity: O(1)
*/

public class P29_ReverseIntegerArrayBasics {
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + digit;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int x : new int[]{123, -123, 120, 1534236469}) {
            System.out.println(x + " -> " + reverse(x));
        }
    }
}
