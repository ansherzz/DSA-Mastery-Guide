import java.util.*;

/*
Problem: Remove Invalid Parentheses
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses
    to make the input string valid. Return a list of unique strings that are valid with the minimum number of removals,
    in any order.

Why this pattern fits:
All optimal answers are required, not just one, so the choices of which parentheses to delete must be enumerated.
    Knowing in advance exactly how many '(' and ')' must go turns a blind search into a tightly bounded one.

Approach:
First pass: count the minimum removals. Scan the string keeping a count of unmatched '('.
    A ')' with no unmatched '(' must be removed (removeRight++); leftover '(' at the end must be removed (removeLeft).
    Search: starting from a given index, try deleting one parenthesis of a type that still needs removals,
    and recurse from the same index on the shortened string.
    Skip a character equal to the previous one at the same level, since deleting either gives the same string.
    When no removals remain, keep the string if it is balanced.

Worked Example:
Example:
    s = "()())()"
    One ')' must be removed. Removing the second or third character of the "())" group gives "(())()" or "()()()".

Time Complexity: O(2^n) worst case, heavily pruned by the removal budget
Space Complexity: O(n) recursion depth
*/

public class RemoveInvalidParenthesesBacktracking {
    public static List<String> removeInvalidParentheses(String s) {
        int removeLeft = 0;
        int removeRight = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                removeLeft++;
            } else if (ch == ')') {
                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }

        List<String> result = new ArrayList<>();
        backtrack(s, 0, removeLeft, removeRight, result);
        return result;
    }

    private static void backtrack(String s, int start, int removeLeft, int removeRight, List<String> result) {
        if (removeLeft == 0 && removeRight == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i > start && ch == s.charAt(i - 1)) {
                continue;
            }

            String shorter = s.substring(0, i) + s.substring(i + 1);
            if (ch == '(' && removeLeft > 0) {
                backtrack(shorter, i, removeLeft - 1, removeRight, result);
            } else if (ch == ')' && removeRight > 0) {
                backtrack(shorter, i, removeLeft, removeRight - 1, result);
            }
        }
    }

    private static boolean isValid(String s) {
        int open = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch == ')' && --open < 0) {
                return false;
            }
        }
        return open == 0;
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")(").equals(Arrays.asList(""))
            ? "[\"\"] (only the empty string)" : "unexpected");
    }
}
