import java.util.*;

/*
Problem: Additive Number
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
An additive number is a string whose digits can form an additive sequence.
    A valid additive sequence contains at least three numbers, and except for the first two,
    each number must be the sum of the preceding two.
    Numbers in the sequence cannot have leading zeros, so "1, 2, 03" is invalid, but the number 0 itself is allowed.
    Given a string containing only digits, return true if it is an additive number.

Why this pattern fits:
Once the first two numbers are chosen, the rest of the sequence is forced.
    So the search is only over the two split points, and each choice is verified by walking the string.

Approach:
Try every length for the first number and every length for the second number.
    Reject a choice if a multi-digit number starts with '0'.
    Then verify: compute the sum of the last two numbers, check that the remaining string starts with that sum,
    advance past it, and repeat until the string is consumed.
    Sums are computed with string addition so that arbitrarily long inputs do not overflow.

Worked Example:
Example:
    num = "199100199"
    First = 1, second = 99: 1 + 99 = 100, 99 + 100 = 199. The whole string is consumed, so the answer is true.

Time Complexity: O(n^3)
Space Complexity: O(n)
*/

public class P35_AdditiveNumberBacktracking {
    public static boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int firstEnd = 1; firstEnd <= n / 2; firstEnd++) {
            if (num.charAt(0) == '0' && firstEnd > 1) {
                break;
            }
            for (int secondEnd = firstEnd + 1; secondEnd < n; secondEnd++) {
                if (num.charAt(firstEnd) == '0' && secondEnd - firstEnd > 1) {
                    break;
                }
                if (follows(num, num.substring(0, firstEnd), num.substring(firstEnd, secondEnd), secondEnd)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean follows(String num, String first, String second, int start) {
        while (start < num.length()) {
            String sum = add(first, second);
            if (!num.startsWith(sum, start)) {
                return false;
            }
            start += sum.length();
            first = second;
            second = sum;
        }
        return true;
    }

    private static String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
        System.out.println(isAdditiveNumber("1023"));
        System.out.println(isAdditiveNumber("000"));
    }
}
