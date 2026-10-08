import java.util.*;

/*
Problem: Arranging Coins
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
You have n coins and want to build a staircase where row i contains exactly i coins.
    The last row may be incomplete. Return the number of complete rows you can build.

Why this pattern fits:
k complete rows need k * (k + 1) / 2 coins, and that amount grows as k grows.
    You are looking for the largest k that still fits, which is a "last true" binary search.

Approach:
Search k in the range [0, n].
    If the coins needed for mid rows fit within n, mid is feasible, so move left up to mid.
    Otherwise move right below mid. Use the upper-middle so the loop always makes progress.

Worked Example:
Example:
    n = 8
    3 rows need 6 coins (fits), 4 rows need 10 coins (does not fit). Answer = 3.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P11_ArrangingCoinsBinarySearch {
    public static int arrangeCoins(int n) {
        long left = 0;
        long right = n;

        while (left < right) {
            long mid = left + (right - left + 1) / 2;
            if (mid * (mid + 1) / 2 <= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }

    private static void runDemo(int n) {
        System.out.println("n = " + n + " -> complete rows: " + arrangeCoins(n));
    }

    public static void main(String[] args) {
        runDemo(5);
        runDemo(8);
        runDemo(1);
        runDemo(2147483647);
    }
}
