import java.util.*;

/*
Problem: Valid Parentheses
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
Given a string consisting only of the characters (), {}, and [],
    return true if the brackets are validly matched and nested.

    Every closing bracket must match the most recent unmatched opening bracket.

Why this pattern fits:
Nested structure is the natural home of a stack.
    The last opening bracket seen is the first one that must be matched.

Approach:
Push opening brackets onto a stack.
    For a closing bracket, check whether the top of the stack is the required matching opener.

Worked Example:
Example:
    s = "({[]})" is valid, while s = "(]" is not.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class ValidParenthesesStack {
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> match = new HashMap<>();
        match.put(')', '(');
        match.put(']', '[');
        match.put('}', '{');

        for (char c : s.toCharArray()) {
            if (match.containsValue(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() != match.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static void runDemo(String s) {
        System.out.println("s = " + s + " -> " + isValid(s));
    }

    public static void main(String[] args) {
        runDemo("()[]{}");
        runDemo("(]");
        runDemo("({[]})");
    }
}
