import java.util.*;

/*
Problem: Multiply Strings
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given two non-negative integers num1 and num2 represented as strings,
    return their product, also represented as a string.
    You must not use a built-in big integer library or convert the inputs to integers directly.

Why this pattern fits:
This is grade-school multiplication simulated on arrays of digits.
    Every digit of one number meets every digit of the other, which is a plain nested loop.

Approach:
The product of an n-digit and an m-digit number has at most n + m digits, so allocate that many slots.
    The digit at index i of num1 times the digit at index j of num2 contributes to slots i + j (carry) and i + j + 1 (ones).
    Process digits from right to left, add each product into slot i + j + 1, and push the carry into slot i + j.
    Finally, skip leading zeros when building the result string.

Worked Example:
Example:
    num1 = "123", num2 = "456"
    123 * 456 = 56088.

Time Complexity: O(n * m)
Space Complexity: O(n + m)
*/

public class MultiplyStringsArrayBasics {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();
        int[] digits = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = product + digits[i + j + 1];
                digits[i + j + 1] = sum % 10;
                digits[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            if (sb.length() == 0 && digit == 0) {
                continue;
            }
            sb.append(digit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
        System.out.println(multiply("999", "999"));
        System.out.println(multiply("0", "52"));
    }
}
