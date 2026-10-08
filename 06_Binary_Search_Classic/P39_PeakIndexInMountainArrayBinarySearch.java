import java.util.*;

/*
Problem: Peak Index in a Mountain Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
A mountain array has length at least 3, strictly increases up to a single peak, and then strictly decreases.
    Given a mountain array, return the index of the peak. The solution must run in O(log n) time.

Why this pattern fits:
The array is not sorted, but the comparison arr[i] < arr[i + 1] is true on the whole uphill side
    and false on the whole downhill side. A true-then-false pattern is all binary search needs.

Approach:
Search the range [0, n - 1].
    If arr[mid] < arr[mid + 1], mid is on the uphill side, so the peak is to the right of mid.
    Otherwise mid is the peak or on the downhill side, so keep mid and discard everything to its right.

Worked Example:
Example:
    arr = [0, 2, 5, 3, 1]
    mid = 2: arr[2] = 5 is not less than arr[3] = 3, so search [0, 2].
    mid = 1: arr[1] = 2 < arr[2] = 5, so search [2, 2]. Answer = 2.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P39_PeakIndexInMountainArrayBinarySearch {
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static void runDemo(int[] arr) {
        System.out.println("Array      : " + Arrays.toString(arr));
        System.out.println("Peak index : " + peakIndexInMountainArray(arr));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{0, 1, 0});
        runDemo(new int[]{0, 2, 5, 3, 1});
        runDemo(new int[]{3, 5, 8, 10, 12, 4});
    }
}
