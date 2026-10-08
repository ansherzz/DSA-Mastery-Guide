import java.util.*;

/*
Problem: Closest Number in a Sorted Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted array and a target, return the array value closest to the target.
If two values are equally close, return the smaller one.

Why this pattern fits:
The closest value must sit immediately left or right of where the target would be inserted.

Approach:
Find the lower bound index i. Candidates are arr[i - 1] and arr[i] (when they exist).
Pick the one with the smaller distance, preferring the left one on a tie.

Worked Example:
arr = [2, 5, 6, 7, 8, 8, 9], target = 4 -> neighbors 2 and 5; 5 is closer. Answer = 5.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P24_ClosestNumberInSortedArrayBinarySearch {
    public static int findClosest(int[] arr, int target) {
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
        if (low == 0) {
            return arr[0];
        }
        if (low == arr.length) {
            return arr[arr.length - 1];
        }
        return target - arr[low - 1] <= arr[low] - target ? arr[low - 1] : arr[low];
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 4, 5, 6, 6, 8, 9], 11 -> " + findClosest(new int[]{1, 2, 4, 5, 6, 6, 8, 9}, 11));
        System.out.println("[2, 5, 6, 7, 8, 8, 9], 4 -> " + findClosest(new int[]{2, 5, 6, 7, 8, 8, 9}, 4));
        System.out.println("[1, 3, 6], 2 -> " + findClosest(new int[]{1, 3, 6}, 2));
    }
}
