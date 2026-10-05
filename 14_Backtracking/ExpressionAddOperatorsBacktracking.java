import java.util.*;

/*
Problem: Expression Add Operators
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Given a string num that contains only digits and an integer target,
    return all the ways to insert the binary operators '+', '-', and '*' between the digits
    so that the resulting expression evaluates to the target.
    Operands in the expressions must not contain leading zeros. Digits may also be joined into multi-digit operands.

Why this pattern fits:
Every gap between digits offers four options: join, add, subtract, or multiply.
    That is an exponential choice tree that must be explored fully, which is backtracking.
    The difficulty is evaluating the expression on the fly while respecting multiplication precedence.

Approach:
Recurse through the string, choosing the next operand (one or more digits, no leading zero).
    Carry two numbers: the value of the expression so far, and the last term that was added to it.
    For '+', the new value is value + operand and the last term is operand.
    For '-', the new value is value - operand and the last term is -operand.
    For '*', undo the last term and redo it multiplied: value - last + last * operand, with last term last * operand.

Worked Example:
Example:
    num = "232", target = 8
    "2+3*2": after "2+3" the value is 5 and the last term is 3.
    Multiplying by 2 gives 5 - 3 + 3 * 2 = 8. The other answer is "2*3+2".

Time Complexity: O(n * 4^n)
Space Complexity: O(n) recursion depth, excluding the output
*/

public class ExpressionAddOperatorsBacktracking {
    public static List<String> addOperators(String num, int target) {
        List<String> answer = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, new StringBuilder(), answer);
        return answer;
    }

    private static void backtrack(String num, int target, int index, long value, long lastTerm,
                                  StringBuilder expression, List<String> answer) {
        if (index == num.length()) {
            if (value == target) {
                answer.add(expression.toString());
            }
            return;
        }

        int lengthBefore = expression.length();
        long operand = 0;

        for (int end = index; end < num.length(); end++) {
            if (end > index && num.charAt(index) == '0') {
                break;
            }
            operand = operand * 10 + (num.charAt(end) - '0');

            if (index == 0) {
                expression.append(operand);
                backtrack(num, target, end + 1, operand, operand, expression, answer);
                expression.setLength(lengthBefore);
                continue;
            }

            expression.append('+').append(operand);
            backtrack(num, target, end + 1, value + operand, operand, expression, answer);
            expression.setLength(lengthBefore);

            expression.append('-').append(operand);
            backtrack(num, target, end + 1, value - operand, -operand, expression, answer);
            expression.setLength(lengthBefore);

            expression.append('*').append(operand);
            backtrack(num, target, end + 1, value - lastTerm + lastTerm * operand, lastTerm * operand, expression, answer);
            expression.setLength(lengthBefore);
        }
    }

    private static void runDemo(String num, int target) {
        System.out.println("num = " + num + ", target = " + target + " -> " + addOperators(num, target));
    }

    public static void main(String[] args) {
        runDemo("123", 6);
        runDemo("232", 8);
        runDemo("105", 5);
        runDemo("3456237490", 9191);
    }
}
