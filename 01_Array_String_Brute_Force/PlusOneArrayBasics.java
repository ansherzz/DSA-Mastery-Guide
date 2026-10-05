import java.util.*;

/*
Problem: Plus One
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
A large non-negative integer is stored as an array of digits, most significant digit first.
    Add one to the integer and return the resulting array of digits.
    The number has no leading zeros except the number 0 itself.

Why this pattern fits:
This is a direct simulation of grade-school addition.
    The only real thinking is the carry, including the case where every digit is 9.

Approach:
Walk from the last digit to the first.
    If a digit is less than 9, increment it and return immediately because the carry stops there.
    Otherwise set it to 0 and keep carrying.
    If the loop finishes, every digit was 9, so the answer is 1 followed by all zeros.

Worked Example:
Example:
    digits = [1, 2, 9] -> the 9 becomes 0 and carries, 2 becomes 3 -> [1, 3, 0]
    digits = [9, 9]    -> both become 0, so a new leading 1 is needed -> [1, 0, 0]

Time Complexity: O(n)
Space Complexity: O(1) unless a new array is required
*/

public class PlusOneArrayBasics {
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    private static void runDemo(int[] digits) {
        System.out.println("Digits : " + Arrays.toString(digits));
        System.out.println("Plus 1 : " + Arrays.toString(plusOne(digits.clone())));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3});
        runDemo(new int[]{1, 2, 9});
        runDemo(new int[]{9, 9});
    }
}
