import java.util.*;

/*
Problem: Minimum Consecutive Cards to Pick Up
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array of card values, return the minimum number of consecutive cards you must pick up
to hold a pair of matching cards, or -1 if it is impossible.

Why this pattern fits:
The shortest valid window always has the matching pair at its two ends,
so the window is fully described by "the last index where this value appeared".

Approach:
Keep a map from value to its most recent index. For each card, if it was seen before,
the window length is i - lastIndex + 1. Track the minimum and update the map.

Worked Example:
cards = [3, 4, 2, 3, 4, 7] -> the 3s are at indices 0 and 3, giving length 4. The 4s also give 4. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P12_MinimumConsecutiveCardsToPickUpSlidingWindow {
    public static int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            Integer previous = lastIndex.put(cards[i], i);
            if (previous != null) {
                best = Math.min(best, i - previous + 1);
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    public static void main(String[] args) {
        System.out.println("[3, 4, 2, 3, 4, 7] -> " + minimumCardPickup(new int[]{3, 4, 2, 3, 4, 7}));
        System.out.println("[1, 0, 5, 3] -> " + minimumCardPickup(new int[]{1, 0, 5, 3}));
    }
}
