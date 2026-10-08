import java.util.*;

/*
Problem: Length of Last Word
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given a string s of words and spaces, return the length of the last word.
A word is a maximal run of non-space characters. The string may end with spaces.

Why this pattern fits:
Splitting the whole string works but allocates every word.
Only the tail of the string matters, so a backward scan answers it directly.

Approach:
Start at the last index and skip spaces.
Then count characters until the next space or the start of the string.

Worked Example:
s = "   fly me   to   the moon  "
Skip the two trailing spaces, then count m-o-o-n. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P09_LengthOfLastWordArrayBasics {
    public static int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        int length = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println("\"Hello World\" -> " + lengthOfLastWord("Hello World"));
        System.out.println("\"   fly me   to   the moon  \" -> " + lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println("\"a\" -> " + lengthOfLastWord("a"));
    }
}
