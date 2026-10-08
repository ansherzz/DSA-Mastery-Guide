import java.util.*;

/*
Problem: Fair Candy Swap
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Alice and Bob each have boxes of candy. They exchange exactly one box each so that both end with the same total.
Return [aliceBox, bobBox] for any valid exchange (one is guaranteed to exist).

Why this pattern fits:
If Alice gives a and receives b, then sumA - a + b = sumB - b + a, so b = a + (sumB - sumA) / 2.
The partner value is fully determined, so it becomes a membership query on Bob's sorted boxes.

Approach:
Sort Bob's sizes. For each of Alice's boxes compute the required b and binary search for it.

Worked Example:
alice = [1, 2], bob = [2, 3] -> difference (5 - 3) / 2 = 1. Alice gives 1, needs b = 2. Answer = [1, 2].

Time Complexity: O((m + n) log n)
Space Complexity: O(1) extra
*/

public class P21_FairCandySwapBinarySearch {
    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = Arrays.stream(aliceSizes).sum();
        int sumB = Arrays.stream(bobSizes).sum();
        int delta = (sumB - sumA) / 2;
        Arrays.sort(bobSizes);
        for (int a : aliceSizes) {
            if (Arrays.binarySearch(bobSizes, a + delta) >= 0) {
                return new int[]{a, a + delta};
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println("[1, 1], [2, 2] -> " + Arrays.toString(fairCandySwap(new int[]{1, 1}, new int[]{2, 2})));
        System.out.println("[1, 2], [2, 3] -> " + Arrays.toString(fairCandySwap(new int[]{1, 2}, new int[]{2, 3})));
        System.out.println("[2], [1, 3] -> " + Arrays.toString(fairCandySwap(new int[]{2}, new int[]{1, 3})));
    }
}
