import java.util.*;

/*
Problem: Count Subarrays With Fixed Bounds
Pattern: 03. Two Pointers
Difficulty: Hard

Interview Prompt:
A fixed-bound subarray has minimum exactly minK and maximum exactly maxK.
Return the number of fixed-bound subarrays of nums.

Why this pattern fits:
For a fixed right end, the valid left ends form one contiguous block.
Three pointers that only move forward describe that block: the last "bad" index and the most recent minK and maxK.

Approach:
Scan right. Update lastBad when the value is outside [minK, maxK], lastMin when it equals minK,
lastMax when it equals maxK. Valid starts lie in (lastBad, min(lastMin, lastMax)], so add
max(0, min(lastMin, lastMax) - lastBad).

Worked Example:
nums = [1, 3, 5, 2, 7, 5], minK = 1, maxK = 5
The valid subarrays are [1, 3, 5] and [1, 3, 5, 2]. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P49_CountSubarraysWithFixedBoundsTwoPointers {
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;
        int lastBad = -1;
        int lastMin = -1;
        int lastMax = -1;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] < minK || nums[right] > maxK) {
                lastBad = right;
            }
            if (nums[right] == minK) {
                lastMin = right;
            }
            if (nums[right] == maxK) {
                lastMax = right;
            }
            count += Math.max(0, Math.min(lastMin, lastMax) - lastBad);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 5, 2, 7, 5], 1, 5 -> " + countSubarrays(new int[]{1, 3, 5, 2, 7, 5}, 1, 5));
        System.out.println("[1, 1, 1, 1], 1, 1 -> " + countSubarrays(new int[]{1, 1, 1, 1}, 1, 1));
    }
}
