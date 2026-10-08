import java.util.*;

/*
Problem: Nth Magical Number
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
A positive integer is magical if it is divisible by either a or b.
    Given three integers n, a, and b, return the n-th magical number.
    Since the answer may be very large, return it modulo 10^9 + 7.

Why this pattern fits:
n can be up to 10^9, so the numbers cannot be generated one at a time.
    The count of magical numbers <= x has a closed form and is non-decreasing in x,
    so the n-th magical number is the smallest x whose count reaches n.

Approach:
Count with inclusion-exclusion: count(x) = x / a + x / b - x / lcm(a, b).
    The n-th magical number is at most n * min(a, b), which bounds the search.
    Binary search for the smallest x with count(x) >= n, then apply the modulus at the very end.
    The search itself must use the true value, not a reduced one.

Worked Example:
Example:
    n = 4, a = 2, b = 3
    Magical numbers: 2, 3, 4, 6, 8, ... The fourth one is 6.

Time Complexity: O(log(n * min(a, b)))
Space Complexity: O(1)
*/

public class P48_NthMagicalNumberBinarySearchAnswer {
    private static final long MOD = 1_000_000_007L;

    public static int nthMagicalNumber(int n, int a, int b) {
        long lcm = (long) a / gcd(a, b) * b;
        long low = 1;
        long high = (long) n * Math.min(a, b);

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = mid / a + mid / b - mid / lcm;
            if (count >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) (low % MOD);
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static void main(String[] args) {
        System.out.println(nthMagicalNumber(1, 2, 3));
        System.out.println(nthMagicalNumber(4, 2, 3));
        System.out.println(nthMagicalNumber(5, 2, 4));
        System.out.println(nthMagicalNumber(1000000000, 40000, 40000));
    }
}
