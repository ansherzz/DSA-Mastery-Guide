import java.util.*;

/*
Problem: Element Appearing More Than 25% In Sorted Array
Pattern: 08. Advanced Search
Difficulty: Easy

Interview Prompt:
In a sorted array exactly one value occurs more than 25% of the time. Return that value.

Why this pattern fits:
A block longer than n / 4 must cover at least one of the quartile positions.
So there are only three candidates, and each is verified with one binary search for its first occurrence.

Approach:
For each candidate value at indices n / 4, n / 2, 3n / 4, find its first index.
If the element n / 4 positions after the first index is still the candidate, it occurs more than 25%.

Worked Example:
arr = [1, 2, 2, 6, 6, 6, 6, 7, 10] -> candidate 6 starts at index 3 and arr[3 + 2] is still 6. Answer = 6.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P04_ElementAppearingMoreThan25PercentInSortedArrayAdvanced {
    private static int firstIndexOf(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int findSpecialInteger(int[] arr) {
        int n = arr.length;
        for (int position : new int[]{n / 4, n / 2, 3 * n / 4}) {
            int candidate = arr[position];
            int first = firstIndexOf(arr, candidate);
            if (first + n / 4 < n && arr[first + n / 4] == candidate) {
                return candidate;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 2, 6, 6, 6, 6, 7, 10] -> " + findSpecialInteger(new int[]{1, 2, 2, 6, 6, 6, 6, 7, 10}));
        System.out.println("[1, 1] -> " + findSpecialInteger(new int[]{1, 1}));
    }
}
