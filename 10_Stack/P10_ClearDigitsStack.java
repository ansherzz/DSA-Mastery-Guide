import java.util.*;

/*
Problem: Clear Digits
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
Repeatedly delete the first digit in the string together with the closest non-digit character to its left.
Return the string after all digits are removed (the input guarantees this is always possible).

Why this pattern fits:
"Remove the most recent surviving character" is a pop. Letters are pushed and each digit pops one.

Approach:
Use a StringBuilder as the stack: append letters, and delete the last character when a digit appears.

Worked Example:
s = "cb34" -> push c, push b, 3 pops b, 4 pops c. Answer = "".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P10_ClearDigitsStack {
    public static String clearDigits(String s) {
        StringBuilder stack = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(ch);
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println("abc -> \"" + clearDigits("abc") + "\"");
        System.out.println("cb34 -> \"" + clearDigits("cb34") + "\"");
    }
}
