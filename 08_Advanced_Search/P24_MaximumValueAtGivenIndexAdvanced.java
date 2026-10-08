import java.util.*;

/*
Problem: Maximum Value at a Given Index in a Bounded Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given three positive integers n, index, and maxSum. Construct an array nums of length n such that:
    - every element is a positive integer
    - adjacent elements differ by at most 1
    - the sum of all elements does not exceed maxSum
    - nums[index] is as large as possible

    Return nums[index] of the constructed array.

Why this pattern fits:
You never build the array. For a chosen peak value v at the index, the cheapest valid array is forced:
    values step down by 1 on both sides until they reach 1, then stay at 1.
    Its sum can be computed with a formula, it grows with v, and so the largest affordable v is binary searched.

Approach:
Write a helper that returns the minimum sum of one side of the peak, given the peak value and the side length.
    If the side is short, it is an arithmetic series v - 1, v - 2, ..., v - length.
    If the side is long, it is the series v - 1 down to 1 plus a tail of ones.
    Binary search v in [1, maxSum] for the largest value whose total (peak + both sides) fits.

Worked Example:
Example:
    n = 4, index = 2, maxSum = 6
    v = 2 gives the array [1, 1, 2, 1] with sum 5, which fits.
    v = 3 gives [1, 2, 3, 2] with sum 8, which does not. Answer = 2.

Time Complexity: O(log maxSum)
Space Complexity: O(1)
*/

public class P24_MaximumValueAtGivenIndexAdvanced {
    private static long sideSum(long peak, long length) {
        long tallest = peak - 1;
        if (tallest >= length) {
            long shortest = peak - length;
            return (tallest + shortest) * length / 2;
        }
        return tallest * (tallest + 1) / 2 + (length - tallest);
    }

    public static int maxValue(int n, int index, int maxSum) {
        int left = 1;
        int right = maxSum;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            long total = mid + sideSum(mid, index) + sideSum(mid, n - index - 1);
            if (total <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void runDemo(int n, int index, int maxSum) {
        System.out.println("n = " + n + ", index = " + index + ", maxSum = " + maxSum);
        System.out.println("Answer : " + maxValue(n, index, maxSum));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(4, 2, 6);
        runDemo(6, 1, 10);
        runDemo(1, 0, 1000000000);
    }
}
