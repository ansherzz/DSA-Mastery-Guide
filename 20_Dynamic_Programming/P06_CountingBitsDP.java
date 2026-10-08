import java.util.*;

/*
Problem: Counting Bits
Pattern: 20. Dynamic Programming
Difficulty: Easy

Interview Prompt:
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
    ans[i] is the number of 1s in the binary representation of i.
    Solve it in O(n) total time, without counting the bits of each number separately.

Why this pattern fits:
The bit count of a number is closely related to the bit count of a smaller number:
    dropping the last bit of i gives i >> 1, whose answer is already known.
    Reusing earlier answers instead of recomputing is the essence of dynamic programming.

Approach:
State: bits[i] is the number of 1s in i.
    Transition: bits[i] = bits[i >> 1] + (i & 1).
    i >> 1 removes the lowest bit, and (i & 1) says whether that removed bit was a 1.
    Fill the table from 1 to n; bits[0] = 0 is the base case.

Worked Example:
Example:
    n = 5
    0 = 0 -> 0, 1 = 1 -> 1, 2 = 10 -> 1, 3 = 11 -> 2, 4 = 100 -> 1, 5 = 101 -> 2.
    Result: [0, 1, 1, 2, 1, 2].

Time Complexity: O(n)
Space Complexity: O(n) for the output
*/

public class P06_CountingBitsDP {
    public static int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(2)));
        System.out.println(Arrays.toString(countBits(5)));
        System.out.println(Arrays.toString(countBits(16)));
    }
}
