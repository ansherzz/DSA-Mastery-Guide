import java.util.*;

/*
Problem: Backspace String Compare
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given two strings s and t, return true if they are equal when both are typed into empty text editors.
    The character '#' means a backspace. Backspacing an empty editor leaves it empty.

    Follow-up: solve it in O(n) time and O(1) extra space.

Why this pattern fits:
A backspace affects characters before it, so scanning from the end lets you know in advance
    how many upcoming characters will be deleted. One pointer per string walks backward in sync.

Approach:
For each string, keep a pointer starting at the end and a counter of pending backspaces.
    Move the pointer left: a '#' increases the counter, and a normal character is skipped if the counter is positive.
    When both pointers rest on surviving characters, compare them.
    The strings are equal only if both run out at the same time.

Worked Example:
Example:
    s = "ab#c", t = "ad#c"
    Both become "ac", so the answer is true.

Time Complexity: O(n + m)
Space Complexity: O(1)
*/

public class P08_BackspaceStringCompareTwoPointers {
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        while (i >= 0 || j >= 0) {
            i = nextSurvivingIndex(s, i);
            j = nextSurvivingIndex(t, j);

            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else if (i >= 0 || j >= 0) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

    private static int nextSurvivingIndex(String text, int index) {
        int skip = 0;
        while (index >= 0) {
            if (text.charAt(index) == '#') {
                skip++;
            } else if (skip > 0) {
                skip--;
            } else {
                break;
            }
            index--;
        }
        return index;
    }

    private static void runDemo(String s, String t) {
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Equal? " + backspaceCompare(s, t));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("ab#c", "ad#c");
        runDemo("ab##", "c#d#");
        runDemo("a#c", "b");
    }
}
