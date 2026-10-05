import java.util.*;

/*
Problem: Decode String
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given an encoded string, return its decoded form.
    The encoding rule is k[encoded_string], meaning the encoded_string inside the brackets is repeated exactly k times.
    k is a positive integer and may have several digits. Encodings can be nested.
    The input is always valid, and digits appear only as repeat counts.

Why this pattern fits:
Nested brackets mean an inner piece must be finished before the outer piece can continue.
    When a '[' appears, the work in progress must be set aside and resumed after the matching ']'.
    "Set aside and resume later" is what a stack does.

Approach:
Keep the current repeat count being read and the current string being built.
    On a digit, extend the count. On '[', push the count and the current string, then reset both.
    On ']', pop the count and the previous string, and append the current string that many times.
    On a letter, append it to the current string.

Worked Example:
Example:
    s = "3[a2[c]]"
    Inside the inner brackets "c" is repeated twice, giving "acc" for the outer body.
    "acc" repeated three times gives "accaccacc".

Time Complexity: O(length of the decoded output)
Space Complexity: O(length of the decoded output)
*/

public class DecodeStringStack {
    public static String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> prefixes = new ArrayDeque<>();

        StringBuilder current = new StringBuilder();
        int count = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            } else if (ch == '[') {
                counts.push(count);
                prefixes.push(current);
                current = new StringBuilder();
                count = 0;
            } else if (ch == ']') {
                int repeat = counts.pop();
                StringBuilder prefix = prefixes.pop();
                for (int i = 0; i < repeat; i++) {
                    prefix.append(current);
                }
                current = prefix;
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }

    private static void runDemo(String s) {
        System.out.println(s + "  ->  " + decodeString(s));
    }

    public static void main(String[] args) {
        runDemo("3[a]2[bc]");
        runDemo("3[a2[c]]");
        runDemo("2[abc]3[cd]ef");
        runDemo("10[x]");
    }
}
