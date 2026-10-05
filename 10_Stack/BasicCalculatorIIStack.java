import java.util.*;

/*
Problem: Basic Calculator II
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a string that represents a valid arithmetic expression, evaluate it and return its value.
    The expression contains non-negative integers, the operators +, -, *, / and spaces.
    Integer division truncates toward zero. There are no parentheses.
    Do not use any built-in expression evaluation function.

Why this pattern fits:
Multiplication and division bind tighter than addition and subtraction.
    A stack lets you postpone the additions: terms are pushed as they appear,
    and a * or / is applied immediately to the most recent term on top of the stack.

Approach:
Track the current number and the operator that precedes it (start with '+').
    When you reach the next operator or the end of the string, apply the previous operator:
    '+' pushes the number, '-' pushes its negation, '*' and '/' replace the top with top * number or top / number.
    The answer is the sum of everything left on the stack.

Worked Example:
Example:
    s = " 3+5 / 2 "
    Push 3. Then push 5. The '/' applies to the top: 5 / 2 = 2.
    Stack holds 3 and 2, so the answer is 5.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class BasicCalculatorIIStack {
    public static int calculate(String s) {
        Deque<Integer> terms = new ArrayDeque<>();
        int number = 0;
        char previousOperator = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }

            boolean isOperator = ch == '+' || ch == '-' || ch == '*' || ch == '/';
            if (isOperator || i == s.length() - 1) {
                switch (previousOperator) {
                    case '+':
                        terms.push(number);
                        break;
                    case '-':
                        terms.push(-number);
                        break;
                    case '*':
                        terms.push(terms.pop() * number);
                        break;
                    default:
                        terms.push(terms.pop() / number);
                }
                previousOperator = ch;
                number = 0;
            }
        }

        int total = 0;
        for (int term : terms) {
            total += term;
        }
        return total;
    }

    private static void runDemo(String s) {
        System.out.println("\"" + s + "\"  =  " + calculate(s));
    }

    public static void main(String[] args) {
        runDemo("3+2*2");
        runDemo(" 3/2 ");
        runDemo(" 3+5 / 2 ");
        runDemo("14-3*2+8/4");
    }
}
