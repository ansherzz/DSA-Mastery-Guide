import java.util.*;

/*
Problem: K-th Element of Two Sorted Arrays
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given two sorted arrays a and b and an integer k (1-indexed),
    return the element that would be at position k if the two arrays were merged into one sorted array.

    Solve it without merging, in O(log(min(n, m))) time.

Why this pattern fits:
This generalizes Median of Two Sorted Arrays. The k smallest elements consist of some prefix of a
    and the matching prefix of b. Once you choose how many come from a, the rest is forced,
    and a wrong choice tells you which direction to move. That is a binary search on the partition point.

Approach:
Always binary search on the shorter array. Let i be the number of elements taken from a and j = k - i from b.
    i must stay within [max(0, k - m), min(k, n)] so that j is a valid count.
    The partition is correct when a[i - 1] <= b[j] and b[j - 1] <= a[i].
    Then the kth element is the larger of the two left-side boundary values.
    If a[i - 1] > b[j], too many were taken from a; otherwise too few.

Worked Example:
Example:
    a = [2, 3, 6, 7, 9], b = [1, 4, 8, 10], k = 5
    Merged: [1, 2, 3, 4, 6, 7, 8, 9, 10]. The 5th element is 6.
    The correct partition takes 2 elements from b (1, 4) and 3 from a (2, 3, 6).

Time Complexity: O(log(min(n, m)))
Space Complexity: O(1)
*/

public class P12_KthElementOfTwoSortedArraysAdvanced {
    public static int kthElement(int[] a, int[] b, int k) {
        if (a.length > b.length) {
            return kthElement(b, a, k);
        }

        int n = a.length;
        int m = b.length;
        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = k - i;

            int aLeft = i > 0 ? a[i - 1] : Integer.MIN_VALUE;
            int aRight = i < n ? a[i] : Integer.MAX_VALUE;
            int bLeft = j > 0 ? b[j - 1] : Integer.MIN_VALUE;
            int bRight = j < m ? b[j] : Integer.MAX_VALUE;

            if (aLeft <= bRight && bLeft <= aRight) {
                return Math.max(aLeft, bLeft);
            }
            if (aLeft > bRight) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return -1;
    }

    private static void runDemo(int[] a, int[] b, int k) {
        System.out.println("a = " + Arrays.toString(a) + ", b = " + Arrays.toString(b) + ", k = " + k);
        System.out.println("Kth element: " + kthElement(a, b, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5);
        runDemo(new int[]{100, 112, 256, 349, 770}, new int[]{72, 86, 113, 119, 265, 445, 892}, 7);
        runDemo(new int[]{1}, new int[]{2, 3}, 3);
    }
}
