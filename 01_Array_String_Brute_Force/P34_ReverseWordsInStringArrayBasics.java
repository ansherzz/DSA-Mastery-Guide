import java.util.*;

/*
Problem: Reverse Words in a String
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a string s, reverse the order of the words.
    A word is a maximal sequence of non-space characters.
    The input may contain leading spaces, trailing spaces, or multiple spaces between words.
    The output must have words separated by exactly one space and no extra spaces at either end.

Why this pattern fits:
This is a careful string scan rather than a clever algorithm.
    Interviewers use it to check that you handle messy whitespace without relying on split and trim.

Approach:
Scan from the end of the string toward the start.
    Skip spaces, then mark the end of a word, then walk left to find its start.
    Append each discovered word to a StringBuilder, adding a single space between words.

Worked Example:
Example:
    s = "  hello   world  "
    Scanning from the right finds "world" first and then "hello".
    Answer = "world hello".

Time Complexity: O(n)
Space Complexity: O(n) for the output
*/

public class P34_ReverseWordsInStringArrayBasics {
    public static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            if (i < 0) {
                break;
            }

            int end = i;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(s, i + 1, end + 1);
        }
        return result.toString();
    }

    private static void runDemo(String s) {
        System.out.println("Input  : \"" + s + "\"");
        System.out.println("Output : \"" + reverseWords(s) + "\"");
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("the sky is blue");
        runDemo("  hello   world  ");
        runDemo("single");
    }
}
