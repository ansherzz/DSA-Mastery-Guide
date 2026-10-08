import java.util.*;

/*
Problem: Minimum Number of Steps to Make Two Strings Anagram
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given two strings s and t of equal length, in one step you can replace any character of t.
Return the minimum number of steps to make t an anagram of s.

Why this pattern fits:
Anagram questions reduce to comparing letter counts.
Every letter that s has more of than t must be created by one replacement.

Approach:
Count letters of s up and letters of t down in one array.
The sum of the positive entries is the number of replacements needed.

Worked Example:
s = "leetcode", t = "practice"
s has surplus letters l, e, e, o, d compared with t. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P27_MinimumStepsToMakeTwoStringsAnagramHashing {
    public static int minSteps(String s, String t) {
        int[] diff = new int[26];
        for (int i = 0; i < s.length(); i++) {
            diff[s.charAt(i) - 'a']++;
            diff[t.charAt(i) - 'a']--;
        }
        int steps = 0;
        for (int value : diff) {
            if (value > 0) {
                steps += value;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println("bab, aba -> " + minSteps("bab", "aba"));
        System.out.println("leetcode, practice -> " + minSteps("leetcode", "practice"));
        System.out.println("anagram, mangaar -> " + minSteps("anagram", "mangaar"));
    }
}
