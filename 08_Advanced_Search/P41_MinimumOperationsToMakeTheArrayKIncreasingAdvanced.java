import java.util.*;

/*
Problem: Minimum Operations to Make the Array K-Increasing
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
An array is K-increasing if arr[i - k] <= arr[i] for every valid i. In one operation you may change any element
to any positive integer. Return the minimum number of operations to make arr K-increasing.

Why this pattern fits:
Indices with the same remainder mod k form independent chains that must each be non-decreasing.
Within a chain, the elements we keep form a longest non-decreasing subsequence; everything else is changed.

Approach:
For each start s < k, collect arr[s], arr[s + k], ... and compute its longest non-decreasing subsequence
with a tails array and an upper-bound search. Add (chain length - LNDS length).

Worked Example:
arr = [4, 1, 5, 2, 6, 2], k = 3 -> chains [4, 2], [1, 6], [5, 2] need 1 + 0 + 1 = 2 changes.

Time Complexity: O(n log(n / k))
Space Complexity: O(n / k)
*/

public class P41_MinimumOperationsToMakeTheArrayKIncreasingAdvanced {
    public static int kIncreasing(int[] arr, int k) {
        int operations = 0;
        for (int start = 0; start < k; start++) {
            List<Integer> tails = new ArrayList<>();
            int length = 0;
            for (int i = start; i < arr.length; i += k) {
                length++;
                int low = 0;
                int high = tails.size();
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (tails.get(mid) <= arr[i]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                if (low == tails.size()) {
                    tails.add(arr[i]);
                } else {
                    tails.set(low, arr[i]);
                }
            }
            operations += length - tails.size();
        }
        return operations;
    }

    public static void main(String[] args) {
        System.out.println("[5, 4, 3, 2, 1], k = 1 -> " + kIncreasing(new int[]{5, 4, 3, 2, 1}, 1));
        System.out.println("[4, 1, 5, 2, 6, 2], k = 2 -> " + kIncreasing(new int[]{4, 1, 5, 2, 6, 2}, 2));
        System.out.println("[4, 1, 5, 2, 6, 2], k = 3 -> " + kIncreasing(new int[]{4, 1, 5, 2, 6, 2}, 3));
    }
}
