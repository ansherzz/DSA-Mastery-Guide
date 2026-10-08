import java.util.*;

/*
Problem: Basic Calculator III
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Evaluate an expression containing non-negative integers, + - * /, parentheses, and spaces.
Division truncates toward zero.

Why this pattern fits:
Operators cannot always be applied when read: in 2 + 3 * 4 the + must wait.
An operator stack postpones them until an operator of lower or equal precedence, a ')', or the end arrives.

Approach:
Numbers go on the value stack. '(' is pushed on the operator stack. ')' applies operators back to the '('.
A new operator first applies every stacked operator of greater or equal precedence, then is pushed.
At the end apply whatever is left.

Worked Example:
"2*(5+5*2)/3+(6/2+8)" -> 2 * 15 / 3 + 11 = 21.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P47_BasicCalculatorIIIStack {
    private static int precedence(char op) {
        return op == '+' || op == '-' ? 1 : 2;
    }

    private static void apply(Deque<Long> values, char op) {
        long right = values.pop();
        long left = values.pop();
        switch (op) {
            case '+': values.push(left + right); break;
            case '-': values.push(left - right); break;
            case '*': values.push(left * right); break;
            default: values.push(left / right);
        }
    }

    public static int calculate(String s) {
        Deque<Long> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                long number = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    number = number * 10 + (s.charAt(i++) - '0');
                }
                i--;
                values.push(number);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    apply(values, operators.pop());
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    apply(values, operators.pop());
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            apply(values, operators.pop());
        }
        return (int) (long) values.pop();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"1+1", "6-4/2", "2*(5+5*2)/3+(6/2+8)", "(2+6*3+5-(3*14/7+2)*5)+3"}) {
            System.out.println(s + " -> " + calculate(s));
        }
    }
}
