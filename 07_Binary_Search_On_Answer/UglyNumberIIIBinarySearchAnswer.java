import java.util.*;

/*
Problem: Ugly Number III
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
An ugly number is a positive integer that is divisible by a, b, or c.
    Given four integers n, a, b, and c, return the n-th ugly number.

Why this pattern fits:
Generating ugly numbers one by one is too slow when n is large.
    But counting how many ugly numbers are <= x is a closed formula, and that count never decreases as x grows.
    So search for the smallest x whose count reaches n.

Approach:
Count with inclusion-exclusion:
    count(x) = x/a + x/b + x/c - x/lcm(a,b) - x/lcm(a,c) - x/lcm(b,c) + x/lcm(a,b,c).
    Binary search x in [1, 2 * 10^9] for the smallest value with count(x) >= n.
    That x is itself ugly, because the count increases exactly at ugly numbers. Use long for the least common multiples.

Worked Example:
Example:
    n = 3, a = 2, b = 3, c = 5
    Ugly numbers: 2, 3, 4, 5, 6, 8, ... The third one is 4.

Time Complexity: O(log(search range))
Space Complexity: O(1)
*/

public class UglyNumberIIIBinarySearchAnswer {
    public static int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(ab, c);

        long low = 1;
        long high = 2_000_000_000L;
        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = mid / a + mid / b + mid / c - mid / ab - mid / ac - mid / bc + mid / abc;
            if (count >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }

    private static long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    private static long lcm(long x, long y) {
        return x / gcd(x, y) * y;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(3, 2, 3, 5));
        System.out.println(nthUglyNumber(4, 2, 3, 4));
        System.out.println(nthUglyNumber(5, 2, 11, 13));
        System.out.println(nthUglyNumber(1000000000, 2, 217983653, 336916467));
    }
}
