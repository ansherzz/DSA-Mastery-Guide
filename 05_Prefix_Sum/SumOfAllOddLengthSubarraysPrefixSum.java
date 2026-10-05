import java.util.*;

/*
Problem: Sum of All Odd Length Subarrays
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Given an array of positive integers, return the sum of all possible odd-length contiguous subarrays.

Why this pattern fits:
The task needs the sum of many different ranges.
    A prefix-sum array turns each range sum into a single subtraction, so enumerating the ranges is cheap.

Approach:
Build prefix where prefix[i] is the sum of the first i elements.
    For every start index, step the end index forward by two so the length stays odd.
    Add prefix[end + 1] - prefix[start] for each such range.

Worked Example:
Example:
    arr = [1, 4, 2, 5, 3]
    Length 1 subarrays sum to 15.
    Length 3 subarrays: [1,4,2] = 7, [4,2,5] = 11, [2,5,3] = 10, total 28.
    Length 5 subarray sums to 15.
    Answer = 15 + 28 + 15 = 58.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class SumOfAllOddLengthSubarraysPrefixSum {
    public static int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int total = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end += 2) {
                total += prefix[end + 1] - prefix[start];
            }
        }
        return total;
    }

    private static void runDemo(int[] arr) {
        System.out.println("Array : " + Arrays.toString(arr));
        System.out.println("Total : " + sumOddLengthSubarrays(arr));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 4, 2, 5, 3});
        runDemo(new int[]{1, 2});
        runDemo(new int[]{10, 11, 12});
    }
}
