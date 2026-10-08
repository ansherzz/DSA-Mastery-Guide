import java.util.*;

/*
Problem: Fraction to Recurring Decimal
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given a numerator and a denominator, return the fraction as a decimal string.
If the fractional part repeats, enclose the repeating part in parentheses.

Why this pattern fits:
Long division repeats as soon as a remainder repeats.
A map from remainder to its position in the output detects the cycle and tells where the parenthesis opens.

Approach:
Handle the sign and the integer part using long arithmetic.
Then repeat: record remainder -> current length, multiply the remainder by 10, append the next digit.
If a remainder is already in the map, insert "(" at its recorded position and append ")".

Worked Example:
4 / 333: remainders go 4, 40, 67, then 4 again, producing digits 0, 1, 2. Answer = "0.(012)".

Time Complexity: O(length of the repeating cycle), at most O(denominator)
Space Complexity: O(denominator)
*/

public class P43_FractionToRecurringDecimalHashing {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        if ((numerator < 0) != (denominator < 0)) {
            result.append('-');
        }
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0) {
            return result.toString();
        }
        result.append('.');
        Map<Long, Integer> position = new HashMap<>();
        while (remainder != 0) {
            if (position.containsKey(remainder)) {
                result.insert(position.get(remainder), "(").append(')');
                break;
            }
            position.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[][] cases = {{1, 2}, {2, 1}, {4, 333}, {1, 6}, {-50, 8}};
        for (int[] c : cases) {
            System.out.println(c[0] + " / " + c[1] + " -> " + fractionToDecimal(c[0], c[1]));
        }
    }
}
