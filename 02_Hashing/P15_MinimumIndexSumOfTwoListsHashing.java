import java.util.*;

/*
Problem: Minimum Index Sum of Two Lists
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
You are given two lists of distinct strings, list1 and list2.
    A common string is one that appears in both lists.
    Return all the common strings with the least index sum, where the index sum of a common string
    is its index in list1 plus its index in list2.

Why this pattern fits:
For each string of the second list you need two facts at once: does it appear in the first list, and at which index?
    A map from string to index answers both in O(1).

Approach:
Build a map from each string of list1 to its index.
    Scan list2. For each string that exists in the map, compute the index sum.
    A smaller sum replaces the current answer list; an equal sum is appended to it.

Worked Example:
Example:
    list1 = [Shogun, Tapioca Express, Burger King, KFC]
    list2 = [KFC, Shogun, Burger King]
    KFC: 3 + 0 = 3, Shogun: 0 + 1 = 1, Burger King: 2 + 2 = 4. Answer: [Shogun].

Time Complexity: O(n + m) string hashes
Space Complexity: O(n)
*/

public class P15_MinimumIndexSumOfTwoListsHashing {
    public static List<String> findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> indexInFirst = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            indexInFirst.put(list1[i], i);
        }

        List<String> result = new ArrayList<>();
        int bestSum = Integer.MAX_VALUE;
        for (int j = 0; j < list2.length; j++) {
            Integer i = indexInFirst.get(list2[j]);
            if (i == null) {
                continue;
            }

            int sum = i + j;
            if (sum < bestSum) {
                bestSum = sum;
                result.clear();
                result.add(list2[j]);
            } else if (sum == bestSum) {
                result.add(list2[j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findRestaurant(
            new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[]{"KFC", "Shogun", "Burger King"}));
        System.out.println(findRestaurant(
            new String[]{"happy", "sad", "good"},
            new String[]{"sad", "happy", "good"}));
    }
}
