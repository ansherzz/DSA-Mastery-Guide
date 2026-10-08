import java.util.*;

/*
Problem: Make The String Great
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
Given a string s of lowercase and uppercase English letters, a good string has no two adjacent characters
    that are the same letter in different cases (such as "aA" or "Bb").
    Repeatedly remove such adjacent pairs until the string is good, and return the result.
    The answer is guaranteed to be unique. An empty string is also good.

Why this pattern fits:
Removing a pair can bring two new characters together, which may form a new bad pair.
    That "cancel with the most recent survivor" behavior is exactly what a stack models.

Approach:
Use a StringBuilder as a stack of surviving characters.
    For each character, compare it with the top of the stack.
    Two characters are the same letter in different cases exactly when their codes differ by 32.
    If they form a bad pair, pop the top; otherwise push the character.

Worked Example:
Example:
    s = "leEeetcode"
    Push l, e. 'E' cancels 'e'. Push e, e, t, c, o, d, e. Result: "leetcode".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P06_MakeTheStringGreatStack {
    public static String makeGood(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int top = stack.length() - 1;
            if (top >= 0 && Math.abs(stack.charAt(top) - ch) == 32) {
                stack.deleteCharAt(top);
            } else {
                stack.append(ch);
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println("[" + makeGood("leEeetcode") + "]");
        System.out.println("[" + makeGood("abBAcC") + "]");
        System.out.println("[" + makeGood("s") + "]");
    }
}
