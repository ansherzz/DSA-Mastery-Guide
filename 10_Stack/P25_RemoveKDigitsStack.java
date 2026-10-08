import java.util.*;

/*
Problem: Remove K Digits
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a string num representing a non-negative integer and an integer k,
    remove k digits from the number so that the remaining number is the smallest possible.
    Return it as a string with no leading zeros. If nothing remains, return "0".

Why this pattern fits:
To make a number small, the leftmost digits matter most.
    Whenever a digit is larger than the digit that follows it, removing the larger one always helps.
    Keeping the kept digits in non-decreasing order is a monotonic stack with a removal budget.

Approach:
Scan the digits. While removals remain and the top of the stack is larger than the current digit, pop the top.
    Push the current digit.
    If removals are still left at the end, the stack is non-decreasing, so drop digits from the end.
    Finally strip leading zeros.

Worked Example:
Example:
    num = "1432219", k = 3
    4 is removed when 3 arrives, 3 is removed when 2 arrives, and one 2 is removed when 1 arrives.
    Remaining digits: 1, 2, 1, 9. Answer = "1219".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P25_RemoveKDigitsStack {
    public static String removeKdigits(String num, int k) {
        StringBuilder stack = new StringBuilder();

        for (char digit : num.toCharArray()) {
            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(digit);
        }

        stack.setLength(Math.max(0, stack.length() - k));

        int start = 0;
        while (start < stack.length() - 1 && stack.charAt(start) == '0') {
            start++;
        }
        String result = stack.substring(start);
        return result.isEmpty() ? "0" : result;
    }

    private static void runDemo(String num, int k) {
        System.out.println("num = " + num + ", k = " + k + " -> " + removeKdigits(num, k));
    }

    public static void main(String[] args) {
        runDemo("1432219", 3);
        runDemo("10200", 1);
        runDemo("10", 2);
        runDemo("12345", 2);
    }
}
