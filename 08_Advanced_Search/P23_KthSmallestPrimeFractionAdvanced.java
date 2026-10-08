import java.util.*;

/*
Problem: K-th Smallest Prime Fraction
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers are unique.
    For every i < j, consider the fraction arr[i] / arr[j].
    Return the k-th smallest fraction considered, as an array [arr[i], arr[j]].

Why this pattern fits:
There are O(n^2) fractions, too many to sort when n is large.
    Instead, binary search a real threshold x in (0, 1) and count how many fractions are <= x.
    The count is monotonic in x, and one linear two-pointer sweep computes it.

Approach:
For a threshold x, sweep the numerator index i from left to right with a denominator pointer j.
    Advance j while arr[i] / arr[j] > x; all denominators from j onward give fractions <= x, adding n - j to the count.
    j never moves backward because larger numerators need larger denominators.
    Also remember the largest fraction that is <= x during the sweep.
    If the count equals k, that largest fraction is the answer. Otherwise move the threshold up or down.

Worked Example:
Example:
    arr = [1, 2, 3, 5], k = 3
    Fractions in order: 1/5, 1/3, 2/5, 1/2, 3/5, 2/3. The third one is 2/5.

Time Complexity: O(n log(1 / precision))
Space Complexity: O(1)
*/

public class P23_KthSmallestPrimeFractionAdvanced {
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double low = 0;
        double high = 1;

        while (true) {
            double mid = (low + high) / 2;
            int count = 0;
            int numerator = 0;
            int denominator = 1;

            int j = 1;
            for (int i = 0; i < n - 1; i++) {
                while (j < n && arr[i] > mid * arr[j]) {
                    j++;
                }
                if (j == n) {
                    break;
                }

                count += n - j;
                if (arr[i] * denominator > numerator * arr[j]) {
                    numerator = arr[i];
                    denominator = arr[j];
                }
            }

            if (count == k) {
                return new int[]{numerator, denominator};
            }
            if (count < k) {
                low = mid;
            } else {
                high = mid;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 7}, 1)));
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 6)));
    }
}
