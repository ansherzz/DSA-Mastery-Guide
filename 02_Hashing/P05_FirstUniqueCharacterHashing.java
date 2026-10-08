import java.util.*;

/*
Problem: First Unique Character in a String
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given a string s of lowercase English letters, return the index of the first character
    that appears exactly once in the string. If no such character exists, return -1.

Why this pattern fits:
Whether a character is unique depends on the whole string, not only on what came before it.
    So count everything first, then use the counts to answer the question in order.

Approach:
First pass: count the frequency of every character.
    Second pass: return the first index whose character has frequency 1.

Worked Example:
Example:
    s = "loveleetcode"
    'l' appears twice, 'o' appears twice, 'v' appears once.
    The first unique character is 'v' at index 2.

Time Complexity: O(n)
Space Complexity: O(1) because the alphabet has a fixed size of 26
*/

public class P05_FirstUniqueCharacterHashing {
    public static int firstUniqChar(String s) {
        int[] frequency = new int[26];
        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (frequency[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    private static void runDemo(String s) {
        System.out.println("String: " + s);
        System.out.println("Index : " + firstUniqChar(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("leetcode");
        runDemo("loveleetcode");
        runDemo("aabb");
    }
}
