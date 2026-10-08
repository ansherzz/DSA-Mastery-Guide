import java.util.*;

/*
Problem: Super Pow
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Compute a^b mod 1337 where a is a positive integer and b is a very large number given as an array of digits.

Why this pattern fits:
The exponent is too large to hold in any integer type, but it can be reduced one digit at a time.
Each step shrinks the exponent by a factor of ten, and the small powers use ordinary fast exponentiation.

Approach:
superPow(a, digits, length): with last digit d and the remaining prefix q,
result = power(superPow(a, digits, length - 1), 10) * power(a, d), all modulo 1337.

Worked Example:
a = 2, b = [1, 0] -> (2^1)^10 * 2^0 = 1024.

Time Complexity: O(number of digits)
Space Complexity: O(number of digits) recursion depth
*/

public class P18_SuperPowDivideConquer {
    private static final int MOD = 1337;

    private static int power(int base, int exponent) {
        int result = 1;
        base %= MOD;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exponent >>= 1;
        }
        return result;
    }

    private static int solve(int a, int[] digits, int length) {
        if (length == 0) {
            return 1;
        }
        int rest = solve(a, digits, length - 1);
        return power(rest, 10) * power(a, digits[length - 1]) % MOD;
    }

    public static int superPow(int a, int[] b) {
        return solve(a % MOD, b, b.length);
    }

    public static void main(String[] args) {
        System.out.println("2 ^ 3 -> " + superPow(2, new int[]{3}));
        System.out.println("2 ^ 10 -> " + superPow(2, new int[]{1, 0}));
        System.out.println("2147483647 ^ 200 -> " + superPow(2147483647, new int[]{2, 0, 0}));
    }
}
