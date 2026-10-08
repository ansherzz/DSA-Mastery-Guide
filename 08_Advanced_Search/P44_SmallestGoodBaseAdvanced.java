import java.util.*;

/*
Problem: Smallest Good Base
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
Given an integer n as a string (up to 10^18), return the smallest base k >= 2
in which n is written using only the digit 1.

Why this pattern fits:
n = 1 + k + k^2 + ... + k^(m-1). More digits means a smaller base, and m is at most about 60.
For a fixed m the sum grows with k, so the base can be bisected.

Approach:
For m from 63 down to 2, binary search k in [2, n^(1/(m-1)) + 1] comparing the geometric sum with n
(stopping early when it would exceed n to avoid overflow). The first match is the smallest base.
m = 2 always succeeds with k = n - 1.

Worked Example:
n = 13 -> with three digits, base 3 gives 1 + 3 + 9 = 13. Answer = "3".

Time Complexity: O(log^2 n) searches, each O(log n) for the sum
Space Complexity: O(1)
*/

public class P44_SmallestGoodBaseAdvanced {
    private static long geometricSum(long base, int terms, long limit) {
        long sum = 1;
        long term = 1;
        for (int i = 1; i < terms; i++) {
            if (term > limit / base) {
                return limit + 1;
            }
            term *= base;
            sum += term;
            if (sum > limit) {
                return limit + 1;
            }
        }
        return sum;
    }

    public static String smallestGoodBase(String text) {
        long n = Long.parseLong(text);
        for (int ones = 63; ones >= 2; ones--) {
            long low = 2;
            long high = (long) Math.pow(n, 1.0 / (ones - 1)) + 1;
            while (low <= high) {
                long mid = low + (high - low) / 2;
                long sum = geometricSum(mid, ones, n);
                if (sum == n) {
                    return String.valueOf(mid);
                }
                if (sum < n) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return String.valueOf(n - 1);
    }

    public static void main(String[] args) {
        for (String n : new String[]{"13", "4681", "1000000000000000000"}) {
            System.out.println(n + " -> " + smallestGoodBase(n));
        }
    }
}
