import java.util.*;

/*
Problem: Sort Characters By Frequency
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given a string s, rearrange it so that characters appear in decreasing order of frequency.
    All copies of the same character must be grouped together.
    If several characters have the same frequency, any relative order between them is acceptable.

Why this pattern fits:
The output depends only on how often each character occurs, so counting comes first.
    Frequencies are bounded by the string length, which allows bucket grouping instead of a comparison sort.

Approach:
Count every character with a hash map.
    Create buckets indexed by frequency, where bucket f holds all characters that appear f times.
    Walk the buckets from the highest frequency down and append each character f times.

Worked Example:
Example:
    s = "tree"
    Counts: e = 2, t = 1, r = 1.
    Bucket 2 holds 'e'; bucket 1 holds 't' and 'r'. One valid answer is "eert".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class SortCharactersByFrequencyHashing {
    public static String frequencySort(String s) {
        Map<Character, Integer> frequency = new TreeMap<>();
        for (char ch : s.toCharArray()) {
            frequency.merge(ch, 1, Integer::sum);
        }

        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= s.length(); i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        StringBuilder result = new StringBuilder();
        for (int count = s.length(); count >= 1; count--) {
            for (char ch : buckets.get(count)) {
                for (int i = 0; i < count; i++) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    private static void runDemo(String s) {
        System.out.println("Input : " + s);
        System.out.println("Sorted: " + frequencySort(s));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("tree");
        runDemo("cccaaa");
        runDemo("Aabb");
    }
}
