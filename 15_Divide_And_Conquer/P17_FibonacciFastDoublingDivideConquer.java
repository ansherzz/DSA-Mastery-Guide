import java.util.*;

/*
Problem: Fibonacci by Fast Doubling
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Compute the n-th Fibonacci number in O(log n) arithmetic operations (n up to 90 fits in a long).

Why this pattern fits:
The identities F(2k) = F(k) * (2 * F(k+1) - F(k)) and F(2k+1) = F(k)^2 + F(k+1)^2
express the answer for n through the answer for n / 2 - the same halving idea as fast exponentiation.

Approach:
pair(n) returns {F(n), F(n+1)}. Base: pair(0) = {0, 1}. Compute pair(n / 2) = {a, b},
then c = a * (2b - a) and d = a^2 + b^2. Return {c, d} when n is even and {d, c + d} when n is odd.

Worked Example:
n = 10 -> pair(5) = {5, 8} -> F(10) = 5 * (16 - 5) = 55.

Time Complexity: O(log n)
Space Complexity: O(log n) recursion depth
*/

public class P17_FibonacciFastDoublingDivideConquer {
    private static long[] pair(int n) {
        if (n == 0) {
            return new long[]{0, 1};
        }
        long[] half = pair(n / 2);
        long a = half[0];
        long b = half[1];
        long c = a * (2 * b - a);
        long d = a * a + b * b;
        return n % 2 == 0 ? new long[]{c, d} : new long[]{d, c + d};
    }

    public static long fibonacci(int n) {
        return pair(n)[0];
    }

    public static void main(String[] args) {
        for (int n : new int[]{10, 50, 90}) {
            System.out.println("F(" + n + ") -> " + fibonacci(n));
        }
    }
}
