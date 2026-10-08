import java.util.*;

/*
Problem: Valid Parenthesis String
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
The string contains '(', ')' and '*', where '*' can act as '(', ')' or an empty string.
Return true if the string can be made valid.

Why this pattern fits:
A ')' prefers a real '(' and falls back to a star. Leftover '(' must each be closed by a star
that appears later, so positions matter - which is why indices are stacked rather than counts.

Approach:
Push indices of '(' and '*' on separate stacks. For ')': pop a '(' if available, otherwise a '*', otherwise fail.
At the end, match each remaining '(' with a remaining '*' that has a larger index.

Worked Example:
"(*))" -> the first ')' uses '(', the second uses '*'. Answer = true.
"*(" -> the star is before the bracket and cannot close it. Answer = false.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P31_ValidParenthesisStringStack {
    public static boolean checkValidString(String s) {
        Deque<Integer> open = new ArrayDeque<>();
        Deque<Integer> stars = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open.push(i);
            } else if (ch == '*') {
                stars.push(i);
            } else if (!open.isEmpty()) {
                open.pop();
            } else if (!stars.isEmpty()) {
                stars.pop();
            } else {
                return false;
            }
        }
        while (!open.isEmpty()) {
            if (stars.isEmpty() || stars.pop() < open.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"()", "(*)", "(*))", "*("}) {
            System.out.println(s + " -> " + checkValidString(s));
        }
    }
}
