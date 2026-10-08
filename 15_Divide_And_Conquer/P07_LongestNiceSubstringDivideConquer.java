import java.util.*;

/*
Problem: Longest Nice Substring
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase.
    For example, "abABB" is nice, but "abA" is not because 'b' appears without 'B'.
    Given a string s, return the longest substring of s that is nice.
    If there are multiple, return the one that occurs earliest. If there are none, return an empty string.

Why this pattern fits:
A character whose other case is missing from the string can never be part of a nice substring.
    So that character is a wall: every nice substring lies entirely on its left or entirely on its right.
    Splitting at the wall produces two independent subproblems.

Approach:
Put all characters of the current string into a set.
    Find the first character whose opposite case is not in the set.
    If there is none, the whole string is nice, so return it.
    Otherwise solve the part before that character and the part after it, and return the longer result,
    preferring the left one on ties so the earliest occurrence wins.

Worked Example:
Example:
    s = "YazaAay"
    'z' has no 'Z' in the string, so split around it into "Ya" and "aAay".
    "Ya" has no nice substring. In "aAay", 'y' has no 'Y', so split again: "aAa" is nice. Answer: "aAa".

Time Complexity: O(n^2) worst case, O(n log n) when the splits are balanced
Space Complexity: O(n) recursion stack
*/

public class P07_LongestNiceSubstringDivideConquer {
    public static String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return "";
        }

        Set<Character> present = new HashSet<>();
        for (char ch : s.toCharArray()) {
            present.add(ch);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char other = Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
            if (!present.contains(other)) {
                String left = longestNiceSubstring(s.substring(0, i));
                String right = longestNiceSubstring(s.substring(i + 1));
                return left.length() >= right.length() ? left : right;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println("[" + longestNiceSubstring("YazaAay") + "]");
        System.out.println("[" + longestNiceSubstring("Bb") + "]");
        System.out.println("[" + longestNiceSubstring("c") + "]");
        System.out.println("[" + longestNiceSubstring("dDzeE") + "]");
    }
}
