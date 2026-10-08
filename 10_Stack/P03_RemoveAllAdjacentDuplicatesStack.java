import java.util.*;

/*
Problem: Remove All Adjacent Duplicates In String
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
You are given a string of lowercase letters. A duplicate removal consists of choosing two adjacent equal letters
    and removing them. Repeat duplicate removals until no more can be made.
    Return the final string. The answer is guaranteed to be unique.

Why this pattern fits:
A removal can bring two previously separated letters together and create a new pair.
    A stack handles that chain reaction naturally: the top of the stack is always the last surviving letter.

Approach:
Use a StringBuilder as a stack of surviving characters.
    For each character, if it equals the last surviving character, remove that character (the pair cancels).
    Otherwise append it.

Worked Example:
Example:
    s = "abbaca"
    'a', 'b' are pushed. The second 'b' cancels the first. The next 'a' cancels the remaining 'a'.
    Then 'c' and 'a' are pushed. Answer = "ca".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P03_RemoveAllAdjacentDuplicatesStack {
    public static String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int top = stack.length() - 1;
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
            } else {
                stack.append(ch);
            }
        }
        return stack.toString();
    }

    private static void runDemo(String s) {
        System.out.println("s = " + s + " -> \"" + removeDuplicates(s) + "\"");
    }

    public static void main(String[] args) {
        runDemo("abbaca");
        runDemo("azxxzy");
        runDemo("aabb");
    }
}
