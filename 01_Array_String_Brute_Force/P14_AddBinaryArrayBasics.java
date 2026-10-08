import java.util.*;

/*
Problem: Add Binary
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given two binary strings a and b, return their sum as a binary string.
The inputs can be far longer than 64 bits, so they cannot be parsed into a number.

Why this pattern fits:
When numbers do not fit a primitive type, simulate the arithmetic digit by digit.

Approach:
Use two indices starting at the ends of both strings and a carry.
At each step add the available digits plus the carry, append sum % 2, and keep sum / 2 as the carry.
Reverse the collected digits at the end.

Worked Example:
a = "1010", b = "1011"
From the right: 0+1=1, 1+1=0 carry 1, 0+0+1=1, 1+1=0 carry 1, then the final carry 1. Answer = "10101".

Time Complexity: O(max(m, n))
Space Complexity: O(max(m, n))
*/

public class P14_AddBinaryArrayBasics {
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            result.append(sum % 2);
            carry = sum / 2;
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("11 + 1 -> " + addBinary("11", "1"));
        System.out.println("1010 + 1011 -> " + addBinary("1010", "1011"));
    }
}
