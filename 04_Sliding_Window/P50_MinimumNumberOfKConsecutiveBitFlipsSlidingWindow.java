import java.util.*;

/*
Problem: Minimum Number of K Consecutive Bit Flips
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
A k-bit flip chooses a subarray of length k and flips every bit in it.
Return the minimum number of k-bit flips needed so that the binary array has no 0, or -1 if impossible.

Why this pattern fits:
The leftmost 0 can only be fixed by a flip starting there, so the choice is forced.
The cost is applying flips: instead of flipping k bits each time, count the flips whose window still covers i.

Approach:
Keep `active` = number of flips started within the last k positions.
The effective bit is nums[i] plus active, modulo 2. If it is 0, start a flip here
(fail if fewer than k positions remain). A flip stops being active k positions after it starts.

Worked Example:
nums = [0, 0, 0, 1, 0, 1, 1, 0], k = 3 -> flips start at indices 0, 4, and 5. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P50_MinimumNumberOfKConsecutiveBitFlipsSlidingWindow {
    public static int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        boolean[] startsFlip = new boolean[n];
        int active = 0;
        int flips = 0;
        for (int i = 0; i < n; i++) {
            if (i >= k && startsFlip[i - k]) {
                active--;
            }
            if ((nums[i] + active) % 2 == 0) {
                if (i + k > n) {
                    return -1;
                }
                startsFlip[i] = true;
                active++;
                flips++;
            }
        }
        return flips;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1, 0], k = 1 -> " + minKBitFlips(new int[]{0, 1, 0}, 1));
        System.out.println("[1, 1, 0], k = 2 -> " + minKBitFlips(new int[]{1, 1, 0}, 2));
        System.out.println("[0, 0, 0, 1, 0, 1, 1, 0], k = 3 -> " + minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }
}
