import java.util.*;

/*
Problem: Valid Anagram
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
    An anagram uses exactly the same characters with exactly the same counts, in any order.

Why this pattern fits:
Two strings are anagrams exactly when they become identical after their characters are put into a canonical order.
    Sorting is the most direct way to produce that canonical order, with no extra data structure to design.

Approach:
If the lengths differ, the answer is false immediately.
    Convert both strings to character arrays, sort each array, and compare them position by position.
    Upgrade path: a 26-slot frequency array removes the sort and brings the time down to O(n).

Worked Example:
Example:
    s = "anagram", t = "nagaram"
    Sorted s = "aaagmnr", sorted t = "aaagmnr". They match, so the answer is true.

Time Complexity: O(n log n)
Space Complexity: O(n) for the character arrays
*/

public class P13_ValidAnagramBruteForce {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] first = s.toCharArray();
        char[] second = t.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
        System.out.println(isAnagram("ab", "abb"));
    }
}
