import java.util.*;

/*
Problem: Karatsuba Multiplication
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Multiply two non-negative integers using the Karatsuba algorithm instead of the built-in multiplication of large values.
    Explain why it is asymptotically faster than grade-school multiplication.

Why this pattern fits:
Splitting each n-digit number into a high half and a low half turns one big multiplication into smaller ones.
    The naive split needs four half-size products and gains nothing.
    Karatsuba's observation is that three are enough, which changes the recurrence and the complexity.

Approach:
Write x = a * 10^m + b and y = c * 10^m + d, where m is half the number of digits.
    Then x * y = ac * 10^(2m) + (ad + bc) * 10^m + bd.
    Compute ac and bd recursively, and get the middle term from one more product:
    (a + b)(c + d) - ac - bd = ad + bc.
    Base case: when either number has a single digit, multiply directly.
    This demo works on long values to keep the idea visible; the same recursion applies to arbitrarily long digit arrays.

Worked Example:
Example:
    1234 * 5678 with m = 2: a = 12, b = 34, c = 56, d = 78.
    ac = 672, bd = 2652, (a + b)(c + d) = 46 * 134 = 6164, middle = 6164 - 672 - 2652 = 2840.
    Result = 672 * 10000 + 2840 * 100 + 2652 = 7006652.

Time Complexity: O(n^log2(3)), about O(n^1.585), for n-digit numbers
Space Complexity: O(log n) recursion depth
*/

public class P20_KaratsubaMultiplicationDivideConquer {
    public static long multiply(long x, long y) {
        if (x < 10 || y < 10) {
            return x * y;
        }

        int digits = Math.max(Long.toString(x).length(), Long.toString(y).length());
        long power = 1;
        for (int i = 0; i < digits / 2; i++) {
            power *= 10;
        }

        long a = x / power;
        long b = x % power;
        long c = y / power;
        long d = y % power;

        long ac = multiply(a, c);
        long bd = multiply(b, d);
        long middle = multiply(a + b, c + d) - ac - bd;
        return ac * power * power + middle * power + bd;
    }

    public static void main(String[] args) {
        System.out.println(multiply(1234, 5678));
        System.out.println(multiply(123456789L, 987654321L) + " (expected " + 123456789L * 987654321L + ")");
        System.out.println(multiply(0, 99999));
    }
}
