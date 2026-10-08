import java.util.*;

/*
Problem: Ransom Note
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given two strings ransomNote and magazine made of lowercase English letters,
    return true if ransomNote can be constructed using letters from magazine.
    Each letter in magazine can be used at most once.

Why this pattern fits:
The question is about supply and demand per character.
    A frequency table of the supply makes each demand check constant time.

Approach:
Count how many of each letter the magazine provides.
    Walk through the note and consume one letter at a time.
    If any letter's remaining count drops below zero, the note cannot be built.

Worked Example:
Example:
    ransomNote = "aa", magazine = "aab"
    Magazine supplies a = 2, b = 1. The note needs a = 2, which is available. Answer = true.

Time Complexity: O(n + m)
Space Complexity: O(1) because the alphabet has a fixed size of 26
*/

public class P14_RansomNoteHashing {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] supply = new int[26];
        for (char ch : magazine.toCharArray()) {
            supply[ch - 'a']++;
        }

        for (char ch : ransomNote.toCharArray()) {
            if (--supply[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(String ransomNote, String magazine) {
        System.out.println("note = " + ransomNote + ", magazine = " + magazine);
        System.out.println("Can construct? " + canConstruct(ransomNote, magazine));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("a", "b");
        runDemo("aa", "ab");
        runDemo("aa", "aab");
    }
}
