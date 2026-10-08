import java.util.*;

/*
Problem: Reverse Substrings Between Each Pair of Parentheses
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Reverse the string inside each pair of matching parentheses, starting from the innermost pair.
The result must not contain any brackets.

Why this pattern fits:
Nested brackets mean "pause the current text, build an inner text, then come back".
Pausing and resuming contexts in last-in-first-out order is what a stack does.

Approach:
Keep a current StringBuilder. On '(' push it and start a new one.
On ')' reverse the current builder and append it to the popped builder, which becomes current again.

Worked Example:
s = "(u(love)i)" -> "love" reversed is "evol", giving "uevoli"; reversing that gives "iloveu".

Time Complexity: O(n^2) in the worst case because of repeated reversals
Space Complexity: O(n)
*/

public class P23_ReverseSubstringsBetweenEachPairOfParenthesesStack {
    public static String reverseParentheses(String s) {
        Deque<StringBuilder> saved = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                saved.push(current);
                current = new StringBuilder();
            } else if (ch == ')') {
                StringBuilder outer = saved.pop();
                outer.append(current.reverse());
                current = outer;
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"(abcd)", "(u(love)i)", "(ed(et(oc))el)"}) {
            System.out.println(s + " -> " + reverseParentheses(s));
        }
    }
}
