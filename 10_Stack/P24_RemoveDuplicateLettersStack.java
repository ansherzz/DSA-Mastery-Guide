import java.util.*;

/*
Problem: Remove Duplicate Letters
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given a string s, remove duplicate letters so that every letter appears once and only once.
    You must make sure your result is the smallest in lexicographical order among all possible results.
    The relative order of the kept letters must follow their order in s.

Why this pattern fits:
A smaller letter should come as early as possible, so when one arrives it wants to evict the larger letters before it.
    Eviction is only allowed if the evicted letter appears again later.
    "Pop while the top is worse and still replaceable" is the monotonic stack template.

Approach:
Record the last index of every letter. Scan the string with a stack of chosen letters and an inStack flag per letter.
    Skip a letter that is already in the stack.
    Otherwise, pop the top while it is greater than the current letter and its last index is still ahead.
    Then push the current letter.
    The stack, read bottom to top, is the answer.

Worked Example:
Example:
    s = "cbacdcbc"
    'a' evicts c and b (both appear later). Then c, d are pushed. 'b' cannot evict d (no later d).
    Result: "acdb".

Time Complexity: O(n)
Space Complexity: O(1), bounded by the alphabet size
*/

public class P24_RemoveDuplicateLettersStack {
    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] inStack = new boolean[26];
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (inStack[ch - 'a']) {
                continue;
            }

            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);
                if (top <= ch || lastIndex[top - 'a'] < i) {
                    break;
                }
                stack.deleteCharAt(stack.length() - 1);
                inStack[top - 'a'] = false;
            }

            stack.append(ch);
            inStack[ch - 'a'] = true;
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("abacb"));
    }
}
