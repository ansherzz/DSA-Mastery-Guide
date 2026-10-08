import java.util.*;

/*
Problem: Pow(x, n)
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Implement pow(x, n), which calculates x raised to the power n.
    n is a 32-bit signed integer and may be negative. Do not use a library power function.

Why this pattern fits:
Multiplying x by itself n times is O(n). The identity x^n = (x^(n/2))^2 halves the problem at every step,
    so only one recursive call is needed and the work drops to O(log n). This is called fast exponentiation.

Approach:
Convert n to a long so that negating Integer.MIN_VALUE does not overflow.
    For a negative exponent, compute the positive power and take its reciprocal.
    Recursively compute half = x^(n / 2). The answer is half * half, times one extra x when n is odd.

Worked Example:
Example:
    2^10 = (2^5)^2, 2^5 = (2^2)^2 * 2, 2^2 = (2^1)^2, 2^1 = (2^0)^2 * 2 = 2.
    Unwinding: 2, 4, 32, 1024. Only four multiplication levels instead of ten.

Time Complexity: O(log n)
Space Complexity: O(log n) recursion stack
*/

public class P16_PowXNDivideConquer {
    public static double myPow(double x, int n) {
        long exponent = n;
        if (exponent < 0) {
            return 1.0 / power(x, -exponent);
        }
        return power(x, exponent);
    }

    private static double power(double x, long exponent) {
        if (exponent == 0) {
            return 1.0;
        }

        double half = power(x, exponent / 2);
        double result = half * half;
        if (exponent % 2 == 1) {
            result *= x;
        }
        return result;
    }

    private static void runDemo(double x, int n) {
        System.out.printf("%.2f ^ %d = %.5f%n", x, n, myPow(x, n));
    }

    public static void main(String[] args) {
        runDemo(2.0, 10);
        runDemo(2.1, 3);
        runDemo(2.0, -2);
        runDemo(1.0, Integer.MIN_VALUE);
    }
}
