import java.util.*;

/*
Problem: Maximum Distance Between a Pair of Values
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Both arrays are non-increasing. A pair (i, j) is valid if i <= j and nums1[i] <= nums2[j]; its distance is j - i.
Return the maximum distance of any valid pair, or 0 if none exists.

Why this pattern fits:
nums2 is sorted in descending order, so "nums2[j] >= value" is true for a prefix of indices.
The last true index is found by binary search - the same template, just on a descending array.

Approach:
For each i search j in [i, n2 - 1] for the last index with nums2[j] >= nums1[i] and update the best j - i.

Worked Example:
nums1 = [55, 30, 5, 4, 2], nums2 = [100, 20, 10, 10, 5] -> pair (2, 4): 5 <= 5 with distance 2.

Time Complexity: O(n1 log n2)
Space Complexity: O(1)
*/

public class P42_MaximumDistanceBetweenAPairOfValuesBinarySearch {
    public static int maxDistance(int[] nums1, int[] nums2) {
        int best = 0;
        for (int i = 0; i < nums1.length && i < nums2.length; i++) {
            int low = i;
            int high = nums2.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums2[mid] >= nums1[i]) {
                    best = Math.max(best, mid - i);
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[55, 30, 5, 4, 2], [100, 20, 10, 10, 5] -> "
                + maxDistance(new int[]{55, 30, 5, 4, 2}, new int[]{100, 20, 10, 10, 5}));
        System.out.println("[2, 2, 2], [10, 10, 1] -> " + maxDistance(new int[]{2, 2, 2}, new int[]{10, 10, 1}));
        System.out.println("[30, 29, 19, 5], [25, 25, 25, 25, 25] -> "
                + maxDistance(new int[]{30, 29, 19, 5}, new int[]{25, 25, 25, 25, 25}));
    }
}
