import java.util.*;

/*
Problem: Valid Anagram
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given two strings s and t, return true if t is an anagram of s.
    Two strings are anagrams if they contain the same characters with the same frequencies.

    Assume the strings use lowercase English letters for this version.

Why this pattern fits:
Anagrams are frequency problems, and frequency problems are classic hashing territory.
    The fastest reasoning path is to count characters and compare the counts.

Approach:
If lengths differ, return false immediately.
    Count each character in s and subtract for each character in t.
    In the end, every count must be zero.

Worked Example:
Example:
    s = "listen", t = "silent"
    Both strings contain the same letters with the same counts, so the answer is true.

Time Complexity: O(n)
Space Complexity: O(1) because the alphabet is fixed
*/

public class P01_ValidAnagramHashing {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(String s, String t) {
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Anagram? " + isAnagram(s, t));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("anagram", "nagaram");
        runDemo("rat", "car");
    }
}
