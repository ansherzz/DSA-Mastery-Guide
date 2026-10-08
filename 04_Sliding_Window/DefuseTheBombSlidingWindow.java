import java.util.*;

/*
Problem: Defuse the Bomb
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
You have a circular array code of length n and a key k. To decrypt the code, replace every number at once:
    - If k > 0, replace the i-th number with the sum of the next k numbers.
    - If k < 0, replace the i-th number with the sum of the previous |k| numbers.
    - If k == 0, replace the i-th number with 0.
    Because the array is circular, the element after the last one is the first one. Return the decrypted code.

Why this pattern fits:
Every answer is the sum of a fixed-length block, and the block for index i + 1 is the block for index i
    shifted by one position. A fixed-size window updates the sum in O(1) per index.

Approach:
Decide the window for index 0: [1, k] when k > 0, or [n - |k|, n - 1] when k < 0.
    Compute its sum once. For each following index, subtract the element leaving on the left,
    add the element entering on the right, and take indices modulo n to wrap around.

Worked Example:
Example:
    code = [5, 7, 1, 4], k = 3
    Index 0: 7 + 1 + 4 = 12. Index 1: 1 + 4 + 5 = 10. Index 2: 4 + 5 + 7 = 16. Index 3: 5 + 7 + 1 = 13.

Time Complexity: O(n)
Space Complexity: O(n) for the result
*/

public class DefuseTheBombSlidingWindow {
    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }

        int start = k > 0 ? 1 : n + k;
        int end = k > 0 ? k : n - 1;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += code[i];
        }

        for (int i = 0; i < n; i++) {
            result[i] = sum;
            sum -= code[(start + i) % n];
            sum += code[(end + i + 1) % n];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(decrypt(new int[]{5, 7, 1, 4}, 3)));
        System.out.println(Arrays.toString(decrypt(new int[]{1, 2, 3, 4}, 0)));
        System.out.println(Arrays.toString(decrypt(new int[]{2, 4, 9, 3}, -2)));
    }
}
