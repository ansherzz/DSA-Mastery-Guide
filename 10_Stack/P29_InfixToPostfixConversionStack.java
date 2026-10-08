import java.util.*;

/*
Problem: Infix to Postfix Conversion (Shunting-Yard)
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Convert an infix expression with single-letter operands, the operators + - * / ^, and parentheses
into postfix (Reverse Polish) notation. ^ is right-associative and has the highest precedence.

Why this pattern fits:
In postfix, an operator appears after its operands, so it must be held back until its right operand is complete.
A stack keeps the pending operators ordered by precedence.

Approach:
Letters are appended to the output. '(' is pushed. ')' pops to the output until '('.
For an operator, first pop every stacked operator with higher precedence
(or equal precedence when the new operator is left-associative), then push it. Flush the stack at the end.

Worked Example:
"a+b*(c^d-e)^(f+g*h)-i" -> "abcd^e-fgh*+^*+i-".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P29_InfixToPostfixConversionStack {
    private static int precedence(char op) {
        switch (op) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return 0;
        }
    }

    public static String infixToPostfix(String expression) {
        StringBuilder output = new StringBuilder();
        Deque<Character> operators = new ArrayDeque<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                output.append(ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    output.append(operators.pop());
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && operators.peek() != '('
                        && (precedence(operators.peek()) > precedence(ch)
                        || (precedence(operators.peek()) == precedence(ch) && ch != '^'))) {
                    output.append(operators.pop());
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            output.append(operators.pop());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println("a+b*(c^d-e)^(f+g*h)-i -> " + infixToPostfix("a+b*(c^d-e)^(f+g*h)-i"));
        System.out.println("(a+b)*c -> " + infixToPostfix("(a+b)*c"));
    }
}
