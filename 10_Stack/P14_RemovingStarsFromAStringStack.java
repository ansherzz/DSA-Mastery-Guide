import java.util.*;

/*
Problem: Removing Stars From a String
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
In one operation, choose a star in s and remove it together with the closest non-star character to its left.
Return the string after all stars have been removed.

Why this pattern fits:
A star acts like a backspace key. The character it removes is always the last one still present, i.e. the stack top.

Approach:
Append non-star characters to a StringBuilder; on '*', delete its last character.

Worked Example:
s = "leet**cod*e" -> "leet" loses "et", "cod" loses "d". Answer = "lecoe".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P14_RemovingStarsFromAStringStack {
    public static String removeStars(String s) {
        StringBuilder stack = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(ch);
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println("leet**cod*e -> \"" + removeStars("leet**cod*e") + "\"");
        System.out.println("erase***** -> \"" + removeStars("erase*****") + "\"");
    }
}
