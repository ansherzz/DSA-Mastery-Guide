import java.util.*;

/*
Problem: Minimum Swaps to Group All 1's Together II
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a circular binary array, return the minimum number of swaps needed
to group all the 1's together at any location. A swap exchanges two distinct positions.

Why this pattern fits:
The final block of ones has a known length (the total number of ones).
Each zero inside the chosen block costs exactly one swap, so we want the window with the most ones.

Approach:
Let k be the count of ones. Slide a window of size k over indices 0..n + k - 1 using i % n for the wrap.
Track the ones inside; the answer is k minus the maximum.

Worked Example:
nums = [0, 1, 1, 1, 0, 0, 1, 1, 0] -> k = 5. The best circular window holds 3 ones, so 2 swaps.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P28_MinimumSwapsToGroupAllOnesTogetherIISlidingWindow {
    public static int minSwaps(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int value : nums) {
            k += value;
        }
        int ones = 0;
        int best = 0;
        for (int i = 0; i < n + k; i++) {
            ones += nums[i % n];
            if (i >= k) {
                ones -= nums[(i - k) % n];
            }
            best = Math.max(best, ones);
        }
        return k - best;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1, 0, 1, 1, 0, 0] -> " + minSwaps(new int[]{0, 1, 0, 1, 1, 0, 0}));
        System.out.println("[0, 1, 1, 1, 0, 0, 1, 1, 0] -> " + minSwaps(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}));
        System.out.println("[1, 1, 0, 0, 1] -> " + minSwaps(new int[]{1, 1, 0, 0, 1}));
    }
}
