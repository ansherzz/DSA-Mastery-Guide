import java.util.*;

/*
Problem: Unique Number of Occurrences
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an integer array, return true if the number of occurrences of each value is unique.

Why this pattern fits:
Two hashing steps chained together: a map to count, then a set to check the counts for duplicates.

Approach:
Build a frequency map. Insert every frequency into a set.
If the set is as large as the map, no two values share the same frequency.

Worked Example:
arr = [1, 2, 2, 1, 1, 3]
Counts are 1 -> 3, 2 -> 2, 3 -> 1. All counts differ, so the answer is true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P08_UniqueNumberOfOccurrencesHashing {
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int value : arr) {
            count.merge(value, 1, Integer::sum);
        }
        return new HashSet<>(count.values()).size() == count.size();
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 2, 1, 1, 3] -> " + uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        System.out.println("[1, 2] -> " + uniqueOccurrences(new int[]{1, 2}));
    }
}
