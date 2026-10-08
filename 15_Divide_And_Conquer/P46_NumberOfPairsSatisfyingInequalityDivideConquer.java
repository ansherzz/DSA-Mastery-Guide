import java.util.*;

/*
Problem: Number of Pairs Satisfying Inequality
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Count pairs (i, j) with i < j and nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.

Why this pattern fits:
Rearranged, the condition is d[i] <= d[j] + diff where d = nums1 - nums2.
Counting ordered pairs under a comparison is the merge-sort counting pattern: both halves are sorted,
so a single moving pointer counts the valid left partners for each right element.

Approach:
Merge sort d. Before merging two sorted halves, for each j in the right half advance a pointer p
over the left half while left[p] <= right[j] + diff, and add p. Then merge as usual.

Worked Example:
nums1 = [3, 2, 5], nums2 = [2, 2, 1], diff = 1 -> d = [1, 0, 4]; all three pairs satisfy the condition.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P46_NumberOfPairsSatisfyingInequalityDivideConquer {
    private static long sortAndCount(long[] d, long[] buffer, int low, int high, int diff) {
        if (low >= high) {
            return 0;
        }
        int mid = (low + high) / 2;
        long count = sortAndCount(d, buffer, low, mid, diff) + sortAndCount(d, buffer, mid + 1, high, diff);
        int p = low;
        for (int j = mid + 1; j <= high; j++) {
            while (p <= mid && d[p] <= d[j] + diff) {
                p++;
            }
            count += p - low;
        }
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i <= mid && (j > high || d[i] <= d[j])) {
                buffer[k] = d[i++];
            } else {
                buffer[k] = d[j++];
            }
        }
        System.arraycopy(buffer, low, d, low, high - low + 1);
        return count;
    }

    public static long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        long[] d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = nums1[i] - nums2[i];
        }
        return sortAndCount(d, new long[n], 0, n - 1, diff);
    }

    public static void main(String[] args) {
        System.out.println("[3, 2, 5] / [2, 2, 1], diff 1 -> " + numberOfPairs(new int[]{3, 2, 5}, new int[]{2, 2, 1}, 1));
        System.out.println("[3, -1] / [-2, 2], diff -1 -> " + numberOfPairs(new int[]{3, -1}, new int[]{-2, 2}, -1));
    }
}
