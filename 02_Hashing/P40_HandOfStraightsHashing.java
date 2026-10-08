import java.util.*;

/*
Problem: Hand of Straights
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given a hand of cards and a group size, return true if the cards can be rearranged
into groups of `groupSize` consecutive values.

Why this pattern fits:
The smallest remaining card must be the start of a group, so we need both counts and sorted order.
A TreeMap is a hash-style counter that also gives the minimum key.

Approach:
Count the cards in a TreeMap. While it is not empty, take the smallest key `start`
and remove one card for each of start, start + 1, ..., start + groupSize - 1. If any is missing, return false.

Worked Example:
hand = [1, 2, 3, 6, 2, 3, 4, 7, 8], groupSize = 3
Groups: [1, 2, 3], [2, 3, 4], [6, 7, 8]. Answer = true.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P40_HandOfStraightsHashing {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int card : hand) {
            count.merge(card, 1, Integer::sum);
        }
        while (!count.isEmpty()) {
            int start = count.firstKey();
            for (int card = start; card < start + groupSize; card++) {
                Integer remaining = count.get(card);
                if (remaining == null) {
                    return false;
                }
                if (remaining == 1) {
                    count.remove(card);
                } else {
                    count.put(card, remaining - 1);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 6, 2, 3, 4, 7, 8], 3 -> " + isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println("[1, 2, 3, 4, 5], 4 -> " + isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
    }
}
