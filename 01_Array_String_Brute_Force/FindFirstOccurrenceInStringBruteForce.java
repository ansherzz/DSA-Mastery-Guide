import java.util.*;

/*
Problem: Find the Index of the First Occurrence in a String
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given two strings haystack and needle, return the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack. An empty needle matches at index 0.

    Do not use the built-in indexOf. Write the matching logic yourself.

Why this pattern fits:
Substring search is naturally "try every starting position, then compare character by character."
    This is the baseline that KMP and rolling-hash approaches later improve on.

Approach:
For every start index where needle could still fit, compare needle against haystack one character at a time.
    If every character matches, return that start index.
    Otherwise move the start one step right and try again.

Worked Example:
Example:
    haystack = "sadbutsad", needle = "sad"
    Start 0 matches "sad" completely, so the answer is 0.

Time Complexity: O((n - m + 1) * m)
Space Complexity: O(1)
*/

public class FindFirstOccurrenceInStringBruteForce {
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int start = 0; start + m <= n; start++) {
            int matched = 0;
            while (matched < m && haystack.charAt(start + matched) == needle.charAt(matched)) {
                matched++;
            }
            if (matched == m) {
                return start;
            }
        }
        return -1;
    }

    private static void runDemo(String haystack, String needle) {
        System.out.println("haystack = " + haystack + ", needle = " + needle);
        System.out.println("Index: " + strStr(haystack, needle));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("sadbutsad", "sad");
        runDemo("leetcode", "leeto");
        runDemo("mississippi", "issip");
    }
}
