import java.util.*;

/*
Problem: Long Pressed Name
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Your friend is typing their name on a keyboard. Sometimes a key gets long pressed,
    and the character is typed one or more times.
    Given the intended name and the typed string, return true if typed could be the name
    with some characters (possibly none) being long pressed.

Why this pattern fits:
Two strings are consumed in parallel with different speeds.
    One pointer per string, each advancing under its own rule, compares them in a single pass.

Approach:
Walk through typed with pointer j while pointer i tracks the next unmatched character of name.
    If typed[j] equals name[i], it matches the next name character, so advance both.
    Otherwise, if typed[j] equals the previous typed character, it is a long press, so advance j only.
    Anything else is an unexplained character, so return false.
    At the end, every character of name must have been matched.

Worked Example:
Example:
    name = "alex", typed = "aaleex"
    a matches, a is a long press, l matches, e matches, e is a long press, x matches. Answer: true.

Time Complexity: O(n + m)
Space Complexity: O(1)
*/

public class P12_LongPressedNameTwoPointers {
    public static boolean isLongPressedName(String name, String typed) {
        int i = 0;
        for (int j = 0; j < typed.length(); j++) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
        }
        return i == name.length();
    }

    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleex"));
        System.out.println(isLongPressedName("saeed", "ssaaedd"));
        System.out.println(isLongPressedName("alex", "aaleexa"));
        System.out.println(isLongPressedName("alex", "ale"));
    }
}
