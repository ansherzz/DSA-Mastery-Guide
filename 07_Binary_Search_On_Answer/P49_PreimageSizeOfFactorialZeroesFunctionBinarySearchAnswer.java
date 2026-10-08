import java.util.*;

/*
Problem: Preimage Size of Factorial Zeroes Function
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Let f(x) be the number of trailing zeros of x!. Given k, return how many non-negative integers x satisfy f(x) == k.

Why this pattern fits:
f(x) = x/5 + x/25 + x/125 + ... is non-decreasing, so the first x with f(x) >= k can be bisected.
f only changes at multiples of 5, so each value that occurs is taken by exactly five consecutive x.

Approach:
Binary search the smallest x with f(x) >= k in [0, 5 * (k + 1)].
If f(x) == k there are exactly 5 such numbers, otherwise k is skipped and the answer is 0.

Worked Example:
k = 0 -> x = 0, 1, 2, 3, 4 (answer 5). k = 5 -> 24! has 4 zeros and 25! has 6, so no x works (answer 0).

Time Complexity: O(log^2 k)
Space Complexity: O(1)
*/

public class P49_PreimageSizeOfFactorialZeroesFunctionBinarySearchAnswer {
    private static long trailingZeroes(long x) {
        long zeros = 0;
        while (x > 0) {
            x /= 5;
            zeros += x;
        }
        return zeros;
    }

    public static int preimageSizeFZF(int k) {
        long low = 0;
        long high = 5L * (k + 1);
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (trailingZeroes(mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return trailingZeroes(low) == k ? 5 : 0;
    }

    public static void main(String[] args) {
        for (int k : new int[]{0, 5, 3}) {
            System.out.println("k = " + k + " -> " + preimageSizeFZF(k));
        }
    }
}
