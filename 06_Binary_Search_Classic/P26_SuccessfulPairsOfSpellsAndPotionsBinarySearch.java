import java.util.*;

/*
Problem: Successful Pairs of Spells and Potions
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given two arrays of positive integers, spells and potions, and a number success.
    A spell and a potion form a successful pair if the product of their strengths is at least success.
    Return an array where the ith value is the number of potions that form a successful pair with the ith spell.

Why this pattern fits:
For a fixed spell, stronger potions are always at least as good as weaker ones.
    After sorting the potions, the successful ones form a suffix, and the start of that suffix
    is found with a lower-bound binary search.

Approach:
Sort the potions.
    For each spell, binary search for the first potion where spell * potion >= success.
    Every potion from that index onward also succeeds, so the count is potions.length - index.
    Use long multiplication to avoid overflow.

Worked Example:
Example:
    spells = [5, 1, 3], potions = [1, 2, 3, 4, 5], success = 7
    Spell 5: products 5, 10, 15, 20, 25 -> 4 successful
    Spell 1: products 1, 2, 3, 4, 5     -> 0 successful
    Spell 3: products 3, 6, 9, 12, 15   -> 3 successful
    Answer = [4, 0, 3]

Time Complexity: O((n + m) log m)
Space Complexity: O(m) for the sorted copy
*/

public class P26_SuccessfulPairsOfSpellsAndPotionsBinarySearch {
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] sortedPotions = potions.clone();
        Arrays.sort(sortedPotions);

        int[] pairs = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int left = 0;
            int right = sortedPotions.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if ((long) spells[i] * sortedPotions[mid] >= success) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            pairs[i] = sortedPotions.length - left;
        }
        return pairs;
    }

    private static void runDemo(int[] spells, int[] potions, long success) {
        System.out.println("Spells : " + Arrays.toString(spells));
        System.out.println("Potions: " + Arrays.toString(potions) + ", success = " + success);
        System.out.println("Pairs  : " + Arrays.toString(successfulPairs(spells, potions, success)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 1, 3}, new int[]{1, 2, 3, 4, 5}, 7);
        runDemo(new int[]{3, 1, 2}, new int[]{8, 5, 8}, 16);
    }
}
