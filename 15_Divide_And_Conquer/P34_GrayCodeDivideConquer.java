import java.util.*;

/*
Problem: Gray Code
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
An n-bit Gray code sequence lists all 2^n integers so that consecutive values (and the first and last)
differ in exactly one bit, starting from 0. Return any valid sequence.

Why this pattern fits:
The n-bit sequence is built from two copies of the (n - 1)-bit sequence:
one as is, and one reversed with the high bit added. Reflection guarantees a one-bit change at the junction.

Approach:
Start with [0]. For each bit b from 0 to n - 1, walk the current list backwards
and append value | (1 << b) for each element.

Worked Example:
n = 2 -> [0, 1] then mirrored with bit 1 set: [0, 1, 3, 2].

Time Complexity: O(2^n)
Space Complexity: O(1) besides the output
*/

public class P34_GrayCodeDivideConquer {
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int bit = 0; bit < n; bit++) {
            for (int i = result.size() - 1; i >= 0; i--) {
                result.add(result.get(i) | (1 << bit));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 2 -> " + grayCode(2));
        System.out.println("n = 3 -> " + grayCode(3));
    }
}
