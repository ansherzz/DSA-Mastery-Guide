import java.util.*;

/*
Problem: Longest Common Prefix with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an array of strings, return the longest prefix that is shared by every string.
    If there is no common prefix, return an empty string.

Why this pattern fits:
A common prefix must agree at position 0, then position 1, and so on for every word.
    Checking column by column across all words is the direct brute-force scan.

Approach:
Use the first word as the reference.
    For each character index of the reference, compare that character against the same index in every other word.
    Stop at the first mismatch or when some word runs out of characters.

Worked Example:
Example:
    words = ["flower", "flow", "flight"]
    Column 0 is 'f' everywhere, column 1 is 'l' everywhere, column 2 differs ('o' vs 'i').
    Answer = "fl".

Time Complexity: O(n * m) where m is the length of the shortest word
Space Complexity: O(1) extra
*/

public class LongestCommonPrefixBruteForce {
    public static String longestCommonPrefix(String[] words) {
        if (words.length == 0) {
            return "";
        }

        String first = words[0];
        for (int col = 0; col < first.length(); col++) {
            char expected = first.charAt(col);
            for (int row = 1; row < words.length; row++) {
                if (col >= words[row].length() || words[row].charAt(col) != expected) {
                    return first.substring(0, col);
                }
            }
        }
        return first;
    }

    private static void runDemo(String[] words) {
        System.out.println("Words : " + Arrays.toString(words));
        System.out.println("Prefix: \"" + longestCommonPrefix(words) + "\"");
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new String[]{"flower", "flow", "flight"});
        runDemo(new String[]{"dog", "racecar", "car"});
        runDemo(new String[]{"interview"});
    }
}
