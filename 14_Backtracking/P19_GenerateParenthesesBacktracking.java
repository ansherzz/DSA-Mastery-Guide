import java.util.*;

/*
Problem: Generate Parentheses
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given n pairs of parentheses, generate all combinations of well-formed parentheses.

Why this pattern fits:
Generating all 2^(2n) strings and filtering would waste most of the work.
    Backtracking builds the string one character at a time and only ever adds a character
    that keeps the prefix valid, so every completed string is automatically well-formed.

Approach:
Track how many '(' and ')' have been placed.
    A '(' may be added while fewer than n have been used.
    A ')' may be added only while it would not outnumber the '(' already placed.
    When the string reaches length 2n, record it.

Worked Example:
Example:
    n = 3
    Result: ((())), (()()), (())(), ()(()), ()()()

Time Complexity: O(4^n / sqrt(n)), the nth Catalan number times the string length
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P19_GenerateParenthesesBacktracking {
    public static List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), answer);
        return answer;
    }

    private static void backtrack(int n, int open, int close, StringBuilder current, List<String> answer) {
        if (current.length() == 2 * n) {
            answer.add(current.toString());
            return;
        }

        if (open < n) {
            current.append('(');
            backtrack(n, open + 1, close, current, answer);
            current.deleteCharAt(current.length() - 1);
        }
        if (close < open) {
            current.append(')');
            backtrack(n, open, close + 1, current, answer);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(4).size() + " combinations for n = 4");
    }
}
