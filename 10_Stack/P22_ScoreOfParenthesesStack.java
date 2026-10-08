import java.util.*;

/*
Problem: Score of Parentheses
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a balanced parentheses string, compute its score: "()" is 1, AB is A + B, and (A) is 2 * A.

Why this pattern fits:
Each open bracket starts a new sub-expression whose score is unknown until it closes.
A stack holds the partial score of every enclosing level.

Approach:
Start with a stack containing 0 (the score of the outermost level).
On '(' push 0. On ')' pop the inner score v and add max(2 * v, 1) to the new top.

Worked Example:
s = "(()(()))" -> inner "()" = 1 and "(())" = 2, sum 3, doubled by the outer pair. Answer = 6.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P22_ScoreOfParenthesesStack {
    public static int scoreOfParentheses(String s) {
        Deque<Integer> scores = new ArrayDeque<>();
        scores.push(0);
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                scores.push(0);
            } else {
                int inner = scores.pop();
                scores.push(scores.pop() + Math.max(2 * inner, 1));
            }
        }
        return scores.pop();
    }

    public static void main(String[] args) {
        for (String s : new String[]{"()", "(())", "()()", "(()(()))"}) {
            System.out.println(s + " -> " + scoreOfParentheses(s));
        }
    }
}
