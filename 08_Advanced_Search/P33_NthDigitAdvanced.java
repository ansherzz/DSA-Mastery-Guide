import java.util.*;

/*
Problem: Nth Digit
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Return the n-th digit of the infinite sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... written as one string.

Why this pattern fits:
The sequence is made of blocks whose sizes are known: 9 numbers of length 1, 90 of length 2, 900 of length 3...
Searching by block size narrows the position exponentially fast, without generating the sequence.

Approach:
Subtract block sizes (length * count) while n is larger than the current block.
Then the target number is start + (n - 1) / length and the digit index inside it is (n - 1) % length.

Worked Example:
n = 11 -> skip the 9 single digits, leaving n = 2 in the two-digit block: number 10, digit index 1 -> 0.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P33_NthDigitAdvanced {
    public static int findNthDigit(int n) {
        long length = 1;
        long count = 9;
        long start = 1;
        long remaining = n;
        while (remaining > length * count) {
            remaining -= length * count;
            length++;
            count *= 10;
            start *= 10;
        }
        long number = start + (remaining - 1) / length;
        return String.valueOf(number).charAt((int) ((remaining - 1) % length)) - '0';
    }

    public static void main(String[] args) {
        for (int n : new int[]{3, 11, 15, 1000}) {
            System.out.println("n = " + n + " -> " + findNthDigit(n));
        }
    }
}
