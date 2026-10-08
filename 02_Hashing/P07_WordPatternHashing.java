import java.util.*;

/*
Problem: Word Pattern
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given a pattern made of lowercase letters and a string s of words separated by single spaces,
    return true if s follows the pattern.

    "Follows" means there is a bijection between letters of the pattern and words of s:
    each letter always maps to the same word, and two different letters never map to the same word.

Why this pattern fits:
This is the same one-to-one mapping check as Isomorphic Strings, but between characters and words.
    A map handles the letter -> word direction and a set tracks which words are already taken.

Approach:
Split s into words. If the number of words differs from the pattern length, return false.
    For each position, if the letter already has a word, it must equal the current word.
    If the letter is new, the current word must not already be used by a different letter.

Worked Example:
Example:
    pattern = "abba", s = "dog cat cat dog"
    a -> dog, b -> cat, b -> cat (consistent), a -> dog (consistent). Answer = true.
    pattern = "abba", s = "dog dog dog dog" fails because a and b would share the word dog.

Time Complexity: O(n + total characters in s)
Space Complexity: O(number of distinct words)
*/

public class P07_WordPatternHashing {
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> wordByLetter = new HashMap<>();
        Set<String> usedWords = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            char letter = pattern.charAt(i);
            String word = words[i];

            String mapped = wordByLetter.get(letter);
            if (mapped == null) {
                if (!usedWords.add(word)) {
                    return false;
                }
                wordByLetter.put(letter, word);
            } else if (!mapped.equals(word)) {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(String pattern, String s) {
        System.out.println("pattern = " + pattern + ", s = " + s);
        System.out.println("Follows? " + wordPattern(pattern, s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("abba", "dog cat cat dog");
        runDemo("abba", "dog cat cat fish");
        runDemo("abba", "dog dog dog dog");
    }
}
