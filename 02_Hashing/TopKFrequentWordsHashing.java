import java.util.*;

/*
Problem: Top K Frequent Words
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an array of strings words and an integer k, return the k most frequent strings.
    Sort the answer by frequency from highest to lowest.
    Words with the same frequency must be sorted in lexicographical order.

Why this pattern fits:
The ranking depends on how often each word occurs, so the first step is always a frequency map.
    Once the counts exist, the remaining work is only an ordering rule.

Approach:
Count every word in a HashMap.
    Copy the distinct words into a list and sort it with a comparator:
    higher count first, and for equal counts the alphabetically smaller word first.
    Return the first k words.
    Upgrade path: a size-k heap with the reversed comparator gives O(n log k).

Worked Example:
Example:
    words = [i, love, leetcode, i, love, coding], k = 2
    Counts: i = 2, love = 2, leetcode = 1, coding = 1.
    "i" and "love" tie on count, and "i" comes first alphabetically. Answer: [i, love].

Time Complexity: O(n + u log u), where u is the number of distinct words
Space Complexity: O(u)
*/

public class TopKFrequentWordsHashing {
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }

        List<String> unique = new ArrayList<>(counts.keySet());
        unique.sort((a, b) -> {
            int byCount = counts.get(b) - counts.get(a);
            return byCount != 0 ? byCount : a.compareTo(b);
        });
        return unique.subList(0, k);
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(
            new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequent(
            new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}
