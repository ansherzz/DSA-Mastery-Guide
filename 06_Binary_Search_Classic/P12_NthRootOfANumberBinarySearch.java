import java.util.*;

/*
Problem: Nth Root of a Number
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given positive integers n and m, return the largest integer r such that r^n <= m
(the floor of the n-th root of m).

Why this pattern fits:
r^n grows monotonically with r, so "is r^n <= m" is a true...true false...false predicate.

Approach:
Search r in [1, m]. Compute r^n by repeated multiplication, stopping early once it exceeds m
so that the product never overflows. Keep the largest r that passes.

Worked Example:
n = 3, m = 27 -> 3. n = 2, m = 17 -> 4 (since 4^2 = 16 <= 17 < 25). n = 4, m = 69 -> 2.

Time Complexity: O(n log m)
Space Complexity: O(1)
*/

public class P12_NthRootOfANumberBinarySearch {
    private static boolean powerAtMost(long base, int exponent, long limit) {
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
            if (result > limit) {
                return false;
            }
        }
        return true;
    }

    public static int nthRoot(int n, int m) {
        int low = 1;
        int high = m;
        int answer = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (powerAtMost(mid, n, m)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("n = 3, m = 27 -> " + nthRoot(3, 27));
        System.out.println("n = 2, m = 17 -> " + nthRoot(2, 17));
        System.out.println("n = 4, m = 69 -> " + nthRoot(4, 69));
    }
}
