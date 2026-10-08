import java.util.*;

/*
Problem: Numbers At Most N Given Digit Set
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Given a sorted set of digits (1-9) that may each be used any number of times,
return how many positive integers can be written that are less than or equal to n.

Why this pattern fits:
Counting numbers up to a bound is done digit by digit: while the prefix equals n's prefix the next digit is restricted
("tight"); once a smaller digit is placed, the remaining positions are completely free. That is digit DP.

Approach:
Let L be the number of digits of n and d the number of allowed digits.
Numbers with fewer digits: d^1 + ... + d^(L-1). For numbers with L digits, at each position i:
every allowed digit smaller than n's digit contributes d^(L-i-1); continue only if n's digit itself is allowed.
If every digit of n is allowed, n itself counts too.

Worked Example:
digits = {1, 3, 5, 7}, n = 100 -> 4 one-digit numbers and 16 two-digit numbers; no three-digit number fits. Answer = 20.

Time Complexity: O(log n * |digits|)
Space Complexity: O(log n)
*/

public class P49_NumbersAtMostNGivenDigitSetDP {
    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String bound = String.valueOf(n);
        int length = bound.length();
        int choices = digits.length;
        int count = 0;
        for (int shorter = 1; shorter < length; shorter++) {
            count += (int) Math.pow(choices, shorter);
        }
        for (int i = 0; i < length; i++) {
            boolean sameDigitAllowed = false;
            for (String digit : digits) {
                if (digit.charAt(0) < bound.charAt(i)) {
                    count += (int) Math.pow(choices, length - i - 1);
                } else if (digit.charAt(0) == bound.charAt(i)) {
                    sameDigitAllowed = true;
                }
            }
            if (!sameDigitAllowed) {
                return count;
            }
        }
        return count + 1;
    }

    public static void main(String[] args) {
        System.out.println("{1, 3, 5, 7}, n = 100 -> " + atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 100));
        System.out.println("{1, 4, 9}, n = 1000000000 -> " + atMostNGivenDigitSet(new String[]{"1", "4", "9"}, 1000000000));
        System.out.println("{7}, n = 8 -> " + atMostNGivenDigitSet(new String[]{"7"}, 8));
    }
}
