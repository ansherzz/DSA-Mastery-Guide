import java.util.*;

/*
Problem: Remove Outermost Parentheses
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
A valid parentheses string can be split into primitive pieces (valid strings that cannot be split further).
Remove the outermost pair of parentheses from every primitive piece and return the result.

Why this pattern fits:
A stack of open brackets is only ever inspected for its size here, so a depth counter is the whole stack.
The outermost brackets are exactly the ones that open from depth 0 or close back to depth 0.

Approach:
For '(': append it if depth > 0, then increase depth.
For ')': decrease depth, then append it if depth > 0.

Worked Example:
s = "(()())(())" -> primitives "(()())" and "(())" become "()()" and "()". Answer = "()()()".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P02_RemoveOutermostParenthesesStack {
    public static String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (depth > 0) {
                    result.append(ch);
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"(()())(())", "()()", "(()())(())(()(()))"}) {
            System.out.println(s + " -> \"" + removeOuterParentheses(s) + "\"");
        }
    }
}
