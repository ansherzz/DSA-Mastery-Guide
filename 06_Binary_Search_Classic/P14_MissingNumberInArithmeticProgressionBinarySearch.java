import java.util.*;

/*
Problem: Missing Number In Arithmetic Progression
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
An arithmetic progression had one value removed (not the first or last).
Given the remaining values in order, return the removed value.

Why this pattern fits:
Before the gap, arr[i] == first + i * step; after it, every value is one step ahead.
That is a monotonic index property, so the first mismatching index can be bisected.

Approach:
The step is (last - first) / n because the original sequence had n + 1 terms.
Binary search for the first index where arr[i] != first + i * step; the missing value is first + thatIndex * step.

Worked Example:
arr = [5, 7, 11, 13] -> step 2. Index 2 holds 11 instead of 9. Answer = 9.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P14_MissingNumberInArithmeticProgressionBinarySearch {
    public static int missingNumber(int[] arr) {
        int n = arr.length;
        int step = (arr[n - 1] - arr[0]) / n;
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == arr[0] + mid * step) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return arr[0] + low * step;
    }

    public static void main(String[] args) {
        System.out.println("[5, 7, 11, 13] -> " + missingNumber(new int[]{5, 7, 11, 13}));
        System.out.println("[15, 13, 12] -> " + missingNumber(new int[]{15, 13, 12}));
    }
}
