import java.util.*;

/*
Problem: Verifying an Alien Dictionary
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
An alien language uses the same 26 letters in a different order.
Given a list of words and the alphabet order, return true if the words are sorted lexicographically in that language.

Why this pattern fits:
Comparing letters requires knowing each letter's position. A letter -> rank table makes that an O(1) lookup.

Approach:
Build rank[letter]. For each adjacent pair of words, find the first differing position:
the pair is in order if the first word's letter has a smaller rank.
If no position differs, the shorter word must come first.

Worked Example:
words = ["word", "world", "row"], order = "worldabcefghijkmnpqstuvxyz"
"word" vs "world" differ at index 3: d comes after l in this alphabet, so the answer is false.

Time Complexity: O(total characters)
Space Complexity: O(1)
*/

public class P22_VerifyingAnAlienDictionaryHashing {
    public static boolean isAlienSorted(String[] words, String order) {
        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }
        for (int w = 0; w + 1 < words.length; w++) {
            String first = words[w];
            String second = words[w + 1];
            int i = 0;
            while (i < first.length() && i < second.length() && first.charAt(i) == second.charAt(i)) {
                i++;
            }
            if (i == second.length() && i < first.length()) {
                return false;
            }
            if (i < first.length() && i < second.length()
                    && rank[first.charAt(i) - 'a'] > rank[second.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[hello, leetcode] -> "
                + isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println("[word, world, row] -> "
                + isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println("[apple, app] -> "
                + isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
