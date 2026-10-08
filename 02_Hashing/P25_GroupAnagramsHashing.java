import java.util.*;

/*
Problem: Group Anagrams
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an array of strings, group together the words that are anagrams of one another.
    Return the groups in any order.

    For example, "eat", "tea", and "ate" belong in the same group.

Why this pattern fits:
Grouping by a canonical signature is a classic hashing move.
    Once every word is mapped to a stable key, words with the same key naturally fall into the same bucket.

Approach:
Sort the characters of each word to create a canonical key.
    Use a hashmap from the sorted key to the list of original words.

Worked Example:
Example:
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
    One valid grouping is [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]].

Time Complexity: O(n * k log k), where k is average word length
Space Complexity: O(n * k)
*/

public class P25_GroupAnagramsHashing {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    private static void runDemo(String[] words) {
        System.out.println("Words  : " + Arrays.toString(words));
        System.out.println("Groups : " + groupAnagrams(words));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        runDemo(new String[]{""});
    }
}
