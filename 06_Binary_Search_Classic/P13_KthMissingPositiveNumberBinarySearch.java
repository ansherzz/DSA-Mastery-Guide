import java.util.*;

/*
Problem: Kth Missing Positive Number
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given an array arr of positive integers sorted in strictly increasing order and an integer k,
    return the kth positive integer that is missing from the array.

    Follow-up: solve it in better than O(n) time.

Why this pattern fits:
If nothing were missing, arr[i] would equal i + 1. So the number of missing values before arr[i]
    is arr[i] - (i + 1). That count never decreases as i grows, so you can binary search on it.

Approach:
Find the first index where the missing count is at least k.
    Call that index left. Exactly `left` array values are smaller than the answer,
    so the kth missing number is k + left.

Worked Example:
Example:
    arr = [2, 3, 4, 7, 11], k = 5
    Missing counts per index: 1, 1, 1, 3, 6. The first count >= 5 is at index 4.
    Answer = 5 + 4 = 9. (The missing numbers are 1, 5, 6, 8, 9.)

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P13_KthMissingPositiveNumberBinarySearch {
    public static int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int missingBefore = arr[mid] - (mid + 1);
            if (missingBefore < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return k + left;
    }

    private static void runDemo(int[] arr, int k) {
        System.out.println("Array : " + Arrays.toString(arr) + ", k = " + k);
        System.out.println("Answer: " + findKthPositive(arr, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, 4, 7, 11}, 5);
        runDemo(new int[]{1, 2, 3, 4}, 2);
    }
}
