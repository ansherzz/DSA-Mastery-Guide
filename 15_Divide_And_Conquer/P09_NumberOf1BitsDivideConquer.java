import java.util.*;

/*
Problem: Number of 1 Bits (Parallel Bit Counting)
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Return the number of set bits in a 32-bit integer without looping over every bit.

Why this pattern fits:
The count for a 32-bit word is the sum of the counts of its two 16-bit halves, and so on down to single bits.
Because all sub-problems of one size sit side by side in the word, each level is solved for all of them at once with a mask.

Approach:
Level 1: add neighboring bits into 2-bit fields. Level 2: add neighboring 2-bit fields into 4-bit fields.
Continue with 8, 16, and 32 bits. Five merge steps replace 32 iterations.

Worked Example:
n = 11 (binary 1011) -> pairs "10" and "11" hold counts 1 and 2, which merge to 3.

Time Complexity: O(log(word size)) = 5 steps
Space Complexity: O(1)
*/

public class P09_NumberOf1BitsDivideConquer {
    public static int hammingWeight(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0F0F0F0F) + ((n >>> 4) & 0x0F0F0F0F);
        n = (n & 0x00FF00FF) + ((n >>> 8) & 0x00FF00FF);
        n = (n & 0x0000FFFF) + ((n >>> 16) & 0x0000FFFF);
        return n;
    }

    public static void main(String[] args) {
        for (int n : new int[]{11, 128, -3}) {
            System.out.println("n = " + n + " -> " + hammingWeight(n));
        }
    }
}
