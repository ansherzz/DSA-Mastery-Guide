import java.util.*;

/*
Problem: Reverse Bits
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Reverse the bits of a given 32-bit unsigned integer.
    Java has no unsigned int type, so the input and output are int values whose bit patterns are what matter.

Why this pattern fits:
Reversing a block of bits can be done by swapping its two halves and then reversing each half.
    The clever part is that every block of the same size can be processed at the same time with one mask,
    so the whole recursion runs level by level in five steps.

Approach:
Level 1: swap the two 16-bit halves.
    Level 2: within each 16-bit block, swap its two 8-bit halves, using the mask 0xff00ff00 and 0x00ff00ff.
    Level 3: swap 4-bit halves within each byte (0xf0f0f0f0 / 0x0f0f0f0f).
    Level 4: swap 2-bit pairs (0xcccccccc / 0x33333333).
    Level 5: swap adjacent bits (0xaaaaaaaa / 0x55555555).
    Use the unsigned shift >>> so that the sign bit is not copied.

Worked Example:
Example:
    n = 43261596  (00000010100101000001111010011100)
    reversed      (00111001011110000010100101000000) = 964176192

Time Complexity: O(1), five mask-and-shift steps
Space Complexity: O(1)
*/

public class ReverseBitsDivideConquer {
    public static int reverseBits(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toUnsignedString(reverseBits(43261596)));
        System.out.println(Integer.toUnsignedString(reverseBits(-3)));
        System.out.println(Integer.toUnsignedString(reverseBits(1)));
    }
}
