import java.util.*;

/*
Problem: Backspace String Compare
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
Given two strings s and t, return true if they are equal when both are typed into empty text editors.
    The character '#' means a backspace. Backspacing on an empty editor leaves it empty.

Why this pattern fits:
A backspace always removes the most recently typed character that is still present.
    Last typed, first removed is the definition of a stack.

Approach:
Simulate each editor with a StringBuilder used as a stack.
    A normal character is pushed. A '#' pops the top if the stack is not empty.
    Compare the two final contents.
    Upgrade path: scanning both strings from the right with skip counters gives O(1) extra space.

Worked Example:
Example:
    s = "ab#c", t = "ad#c"
    Both editors end up containing "ac", so the answer is true.

Time Complexity: O(n + m)
Space Complexity: O(n + m)
*/

public class BackspaceStringCompareStack {
    public static boolean backspaceCompare(String s, String t) {
        return type(s).equals(type(t));
    }

    private static String type(String keys) {
        StringBuilder stack = new StringBuilder();
        for (char key : keys.toCharArray()) {
            if (key != '#') {
                stack.append(key);
            } else if (stack.length() > 0) {
                stack.deleteCharAt(stack.length() - 1);
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab##", "c#d#"));
        System.out.println(backspaceCompare("a#c", "b"));
    }
}
