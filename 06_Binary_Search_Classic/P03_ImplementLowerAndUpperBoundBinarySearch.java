import java.util.*;

/*
Problem: Implement Lower Bound and Upper Bound
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted array and a value x, implement:
lowerBound - the first index whose value is >= x, and upperBound - the first index whose value is > x.
Both return the array length when no such index exists.

Why this pattern fits:
Most binary search problems are one of these two boundaries in disguise
(insert position, first/last occurrence, count of elements below x, floor and ceiling).

Approach:
Search the half-open range [low, high) with high = n.
For lowerBound move right while arr[mid] < x; for upperBound move right while arr[mid] <= x.
The loop ends with low == high at the boundary.

Worked Example:
arr = [1, 2, 2, 2, 5]: lowerBound(2) = 1, upperBound(2) = 4, so 2 occurs 4 - 1 = 3 times.
lowerBound(3) = upperBound(3) = 4. lowerBound(6) = 5 (past the end).

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P03_ImplementLowerAndUpperBoundBinarySearch {
    public static int lowerBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 5};
        for (int x : new int[]{2, 3, 6}) {
            System.out.println("lowerBound(" + x + ") -> " + lowerBound(arr, x));
            System.out.println("upperBound(" + x + ") -> " + upperBound(arr, x));
        }
    }
}
