import java.util.*;

/*
Problem: Basic Calculator
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Given a string s representing a valid expression, implement a basic calculator to evaluate it and return the result.
    The expression contains non-negative integers, '+', '-', '(', ')', and spaces.
    '-' may be used as a unary operator, as in "-1" or "-(2 + 3)". You may not use any built-in expression evaluator.

Why this pattern fits:
Parentheses nest, and when one closes the calculation must resume exactly where the enclosing level left off.
    Saving the outer state on a stack when '(' opens and restoring it when ')' closes handles any depth.

Approach:
Keep a running result and the sign (+1 or -1) to apply to the next number.
    - Digit: read the whole number and add sign * number to the result.
    - '+' or '-': set the sign.
    - '(': push the current result and the sign that applies to the parenthesized group, then reset both.
    - ')': the result is now the value of the group. Pop the sign and the outer result:
      result = outerResult + groupSign * result.
    A unary minus needs no special case because the result starts at 0.

Worked Example:
Example:
    s = "(1+(4+5+2)-3)+(6+8)"
    Inner group: 4 + 5 + 2 = 11. First group: 1 + 11 - 3 = 9. Second group: 14. Total = 23.

Time Complexity: O(n)
Space Complexity: O(n) for the nesting depth
*/

public class P29_BasicCalculatorStack {
    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int number = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    number = number * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                result += sign * number;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                int groupSign = stack.pop();
                int outerResult = stack.pop();
                result = outerResult + groupSign * result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("-(2 + 3) - (4 - 10)"));
    }
}
