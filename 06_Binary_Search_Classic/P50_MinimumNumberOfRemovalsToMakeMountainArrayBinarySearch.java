import java.util.*;

/*
Problem: Minimum Number of Removals to Make Mountain Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Hard

Interview Prompt:
A mountain array strictly increases to a peak and then strictly decreases, with at least one element on each side.
Return the minimum number of elements to remove from nums to make it a mountain array.

Why this pattern fits:
Keeping the most elements means choosing a peak i that maximizes
(longest increasing subsequence ending at i) + (longest decreasing subsequence starting at i) - 1.
Both come from the O(n log n) binary-search LIS.

Approach:
Run the tails-array LIS left to right to get left[i], and right to left to get right[i].
Only indices with left[i] > 1 and right[i] > 1 can be peaks. Answer = n - max(left[i] + right[i] - 1).

Worked Example:
nums = [2, 1, 1, 5, 6, 2, 3, 1] -> keep [1, 5, 6, 3, 1] (peak 6), removing 3 elements.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P50_MinimumNumberOfRemovalsToMakeMountainArrayBinarySearch {
    private static int[] lisEndingAt(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int size = 0;
        int[] length = new int[n];
        for (int i = 0; i < n; i++) {
            int low = 0;
            int high = size;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < nums[i]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            tails[low] = nums[i];
            if (low == size) {
                size++;
            }
            length[i] = low + 1;
        }
        return length;
    }

    public static int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        int[] left = lisEndingAt(nums);
        int[] rightReversed = lisEndingAt(reversed);
        int best = 0;
        for (int i = 0; i < n; i++) {
            int right = rightReversed[n - 1 - i];
            if (left[i] > 1 && right > 1) {
                best = Math.max(best, left[i] + right - 1);
            }
        }
        return n - best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 1] -> " + minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println("[2, 1, 1, 5, 6, 2, 3, 1] -> " + minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
    }
}
