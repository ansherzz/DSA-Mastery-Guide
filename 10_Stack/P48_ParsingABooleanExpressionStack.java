import java.util.*;

/*
Problem: Parsing A Boolean Expression
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Evaluate a boolean expression made of 't', 'f', !(expr), &(expr, expr, ...), and |(expr, expr, ...).

Why this pattern fits:
The expression is nested prefix notation. A closing bracket means the innermost group is complete,
and its operands are exactly the items on the stack above the matching '('.

Approach:
Push every character except commas. On ')': pop values until '(' noting whether a 't' and an 'f' were seen,
pop '(' and the operator before it, compute the result, and push it as 't' or 'f'.

Worked Example:
"!(&(f,t))" -> &(f, t) = f, then !(f) = t. Answer = true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P48_ParsingABooleanExpressionStack {
    public static boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : expression.toCharArray()) {
            if (ch == ',') {
                continue;
            }
            if (ch != ')') {
                stack.push(ch);
                continue;
            }
            boolean sawTrue = false;
            boolean sawFalse = false;
            while (stack.peek() != '(') {
                if (stack.pop() == 't') {
                    sawTrue = true;
                } else {
                    sawFalse = true;
                }
            }
            stack.pop();
            char operator = stack.pop();
            boolean result;
            if (operator == '!') {
                result = sawFalse;
            } else if (operator == '&') {
                result = !sawFalse;
            } else {
                result = sawTrue;
            }
            stack.push(result ? 't' : 'f');
        }
        return stack.pop() == 't';
    }

    public static void main(String[] args) {
        for (String s : new String[]{"&(|(f))", "|(f,f,f,t)", "!(&(f,t))"}) {
            System.out.println(s + " -> " + parseBoolExpr(s));
        }
    }
}
