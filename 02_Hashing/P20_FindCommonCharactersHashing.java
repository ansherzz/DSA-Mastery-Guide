import java.util.*;

/*
Problem: Find Common Characters
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an array of lowercase words, return every character that appears in all of the words,
including duplicates (a letter appearing twice in every word is returned twice).

Why this pattern fits:
The intersection of multisets is the per-key minimum of their frequency tables.

Approach:
Start with the letter counts of the first word.
For every other word, lower each count to the minimum of the two tables.
Emit each letter as many times as its final count.

Worked Example:
words = ["bella", "label", "roller"]
e appears at least once and l at least twice in every word. Answer = [e, l, l].

Time Complexity: O(total characters)
Space Complexity: O(1)
*/

public class P20_FindCommonCharactersHashing {
    public static List<Character> commonChars(String[] words) {
        int[] common = new int[26];
        Arrays.fill(common, Integer.MAX_VALUE);
        for (String word : words) {
            int[] count = new int[26];
            for (char ch : word.toCharArray()) {
                count[ch - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                common[i] = Math.min(common[i], count[i]);
            }
        }
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < common[i]; k++) {
                result.add((char) ('a' + i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[bella, label, roller] -> " + commonChars(new String[]{"bella", "label", "roller"}));
        System.out.println("[cool, lock, cook] -> " + commonChars(new String[]{"cool", "lock", "cook"}));
    }
}
