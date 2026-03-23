import java.util.*;

/*
Problem: Evaluate Reverse Polish Notation
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Evaluate an arithmetic expression written in Reverse Polish Notation.
    Valid operators are +, -, *, and /, and division should truncate toward zero.

    Example: ["2", "1", "+", "3", "*"] evaluates to 9.

Why this pattern fits:
Reverse Polish Notation is naturally stack-shaped because operators consume the most recent operands.

Approach:
Push numbers onto a stack.
    When an operator appears, pop the top two numbers in the correct order, apply the operator,
    and push the result back.

Worked Example:
Example:
    tokens = ["4", "13", "5", "/", "+"]
    The result is 6 because 13 / 5 = 2, then 4 + 2 = 6.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class EvaluateReversePolishNotationStack {
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private static void runDemo(String[] tokens) {
        System.out.println("Tokens : " + Arrays.toString(tokens));
        System.out.println("Value  : " + evalRPN(tokens));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new String[]{"2", "1", "+", "3", "*"});
        runDemo(new String[]{"4", "13", "5", "/", "+"});
    }
}
