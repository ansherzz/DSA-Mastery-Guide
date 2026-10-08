import java.util.*;

/*
Problem: Find All Anagrams in a String
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given two strings s and p of lowercase English letters, return all start indices in s
    where a substring is an anagram of p. The indices may be returned in any order.

Why this pattern fits:
An anagram of p has exactly p.length() characters, so every candidate is a fixed-length window.
    Two windows that overlap differ by one outgoing and one incoming character,
    so the letter counts can be maintained incrementally.

Approach:
Count the letters of p. Maintain letter counts for the current window in s.
    Slide the window across s: add the new character, and once the window is longer than p, remove the oldest one.
    Whenever the window has the right length and its counts equal p's counts, record the start index.

Worked Example:
Example:
    s = "cbaebabacd", p = "abc"
    "cba" at index 0 and "bac" at index 6 are anagrams of "abc". Answer = [0, 6].

Time Complexity: O(n * 26), which is O(n)
Space Complexity: O(1) because the alphabet has a fixed size of 26
*/

public class P17_FindAllAnagramsSlidingWindow {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> starts = new ArrayList<>();
        if (p.length() > s.length()) {
            return starts;
        }

        int[] need = new int[26];
        int[] window = new int[26];
        for (char ch : p.toCharArray()) {
            need[ch - 'a']++;
        }

        for (int right = 0; right < s.length(); right++) {
            window[s.charAt(right) - 'a']++;
            if (right >= p.length()) {
                window[s.charAt(right - p.length()) - 'a']--;
            }
            if (right >= p.length() - 1 && Arrays.equals(need, window)) {
                starts.add(right - p.length() + 1);
            }
        }
        return starts;
    }

    private static void runDemo(String s, String p) {
        System.out.println("s = " + s + ", p = " + p);
        System.out.println("Starts: " + findAnagrams(s, p));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("cbaebabacd", "abc");
        runDemo("abab", "ab");
    }
}
