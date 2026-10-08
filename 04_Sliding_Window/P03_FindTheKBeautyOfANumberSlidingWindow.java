import java.util.*;

/*
Problem: Find the K-Beauty of a Number
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
The k-beauty of an integer num is the number of length-k substrings of its decimal form
that are divisors of num. Leading zeros are allowed, and 0 is not a divisor of anything.

Why this pattern fits:
"Every substring of length k" is the definition of a fixed-size window.

Approach:
Convert num to a string. For each start index take the k-digit window, parse it,
and count it when it is non-zero and divides num.

Worked Example:
num = 430043, k = 2 -> windows 43, 30, 00, 04, 43. Only the two 43s divide 430043. Answer = 2.

Time Complexity: O(n * k)
Space Complexity: O(n)
*/

public class P03_FindTheKBeautyOfANumberSlidingWindow {
    public static int divisorSubstrings(int num, int k) {
        String digits = String.valueOf(num);
        int count = 0;
        for (int start = 0; start + k <= digits.length(); start++) {
            int value = Integer.parseInt(digits.substring(start, start + k));
            if (value != 0 && num % value == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("240, k = 2 -> " + divisorSubstrings(240, 2));
        System.out.println("430043, k = 2 -> " + divisorSubstrings(430043, 2));
    }
}
