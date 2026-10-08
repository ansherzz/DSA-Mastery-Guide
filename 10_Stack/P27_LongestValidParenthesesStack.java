import java.util.*;

/*
Problem: Longest Valid Parentheses
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Given a string containing only the characters '(' and ')',
    return the length of the longest substring that forms valid (well-formed) parentheses.

Why this pattern fits:
A plain validity check uses a stack of open brackets. To measure lengths, store indices instead of characters.
    The index on top of the stack then always marks the position just before the current valid stretch.

Approach:
Start the stack with -1 as a base index.
    For '(', push its index. For ')', pop once.
    If the stack becomes empty, this ')' is unmatched, so push its index as the new base.
    Otherwise the current valid stretch runs from (top + 1) to the current index, with length i - top.

Worked Example:
Example:
    s = ")()())"
    Index 0 is unmatched and becomes the base. Indices 1..4 form "()()".
    At index 4 the top of the stack is 0, so the length is 4 - 0 = 4. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P27_LongestValidParenthesesStack {
    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int best = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    best = Math.max(best, i - stack.peek());
                }
            }
        }
        return best;
    }

    private static void runDemo(String s) {
        System.out.println("\"" + s + "\" -> " + longestValidParentheses(s));
    }

    public static void main(String[] args) {
        runDemo("(()");
        runDemo(")()())");
        runDemo("");
        runDemo("()(())");
    }
}
