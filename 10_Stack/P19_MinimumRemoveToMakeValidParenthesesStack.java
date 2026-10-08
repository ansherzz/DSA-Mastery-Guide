import java.util.*;

/*
Problem: Minimum Remove to Make Valid Parentheses
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a string of lowercase letters and the characters '(' and ')',
    remove the minimum number of parentheses so that the resulting string is valid, and return any valid result.
    A string is valid when every '(' has a matching ')' after it and every ')' has a matching '(' before it.

Why this pattern fits:
Matching parentheses is the textbook stack problem. Here the stack stores the positions of unmatched '('
    so that you know exactly which characters must be deleted.

Approach:
Scan the string. Push the index of every '('.
    For a ')', pop a matching '(' if one exists; otherwise mark this ')' for removal.
    After the scan, every index still on the stack is an unmatched '(' and is also marked for removal.
    Build the answer from the unmarked characters.

Worked Example:
Example:
    s = "lee(t(c)o)de)"
    The last ')' has no partner and is removed. Answer = "lee(t(c)o)de".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P19_MinimumRemoveToMakeValidParenthesesStack {
    public static String minRemoveToMakeValid(String s) {
        boolean[] remove = new boolean[s.length()];
        Deque<Integer> openIndices = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openIndices.push(i);
            } else if (ch == ')') {
                if (openIndices.isEmpty()) {
                    remove[i] = true;
                } else {
                    openIndices.pop();
                }
            }
        }
        for (int index : openIndices) {
            remove[index] = true;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!remove[i]) {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    private static void runDemo(String s) {
        System.out.println("\"" + s + "\"  ->  \"" + minRemoveToMakeValid(s) + "\"");
    }

    public static void main(String[] args) {
        runDemo("lee(t(c)o)de)");
        runDemo("a)b(c)d");
        runDemo("))((");
    }
}
