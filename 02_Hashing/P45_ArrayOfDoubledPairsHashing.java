import java.util.*;

/*
Problem: Array of Doubled Pairs
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an even-length integer array, return true if it can be reordered so that
arr[2i + 1] == 2 * arr[2i] for every i.

Why this pattern fits:
Each value needs a partner, and the value with the smallest magnitude has only one possible partner.
A sorted frequency map lets us match greedily without ambiguity.

Approach:
Count values in a TreeMap and iterate in ascending order.
A positive x must be matched with 2x; a negative x (the smallest remaining) must be matched with x / 2,
so it must be even. Zeros must pair with each other. Fail if the partner count is too small.

Worked Example:
arr = [4, -2, 2, -4]
-4 pairs with -2, and 2 pairs with 4. Answer = true.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P45_ArrayOfDoubledPairsHashing {
    public static boolean canReorderDoubled(int[] arr) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int value : arr) {
            count.merge(value, 1, Integer::sum);
        }
        for (int value : count.keySet()) {
            int have = count.get(value);
            if (have == 0) {
                continue;
            }
            if (value == 0) {
                if (have % 2 != 0) {
                    return false;
                }
                continue;
            }
            if (value < 0 && value % 2 != 0) {
                return false;
            }
            int partner = value < 0 ? value / 2 : value * 2;
            if (count.getOrDefault(partner, 0) < have) {
                return false;
            }
            count.put(partner, count.get(partner) - have);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[3, 1, 3, 6] -> " + canReorderDoubled(new int[]{3, 1, 3, 6}));
        System.out.println("[4, -2, 2, -4] -> " + canReorderDoubled(new int[]{4, -2, 2, -4}));
        System.out.println("[2, 1, 2, 6] -> " + canReorderDoubled(new int[]{2, 1, 2, 6}));
    }
}
