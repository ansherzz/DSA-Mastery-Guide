import java.util.*;

/*
Problem: Square Root with Decimal Precision
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given a non-negative real number x, compute its square root accurate to at least 5 decimal places
without using a library square-root function.

Why this pattern fits:
mid * mid is monotonic in mid, so the root can be bracketed and the bracket halved.
On real numbers there is no "mid + 1": the loop runs a fixed number of iterations (or until the interval is tiny).

Approach:
Search in [0, max(1, x)] (the root of a number below 1 is larger than the number itself).
Repeat 100 times: if mid * mid < x move low to mid, else move high to mid. Return low.

Worked Example:
x = 2 -> the interval shrinks around 1.41421...
x = 0.25 -> the answer 0.5 is larger than x, which is why the upper bound must be at least 1.

Time Complexity: O(iterations), about 100 steps
Space Complexity: O(1)
*/

public class P29_SquareRootWithPrecisionBinarySearch {
    public static double squareRoot(double x) {
        double low = 0;
        double high = Math.max(1, x);
        for (int step = 0; step < 100; step++) {
            double mid = (low + high) / 2;
            if (mid * mid < x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        for (double x : new double[]{2, 0.25, 10}) {
            System.out.println("sqrt(" + x + ") -> " + String.format(Locale.ROOT, "%.5f", squareRoot(x)));
        }
    }
}
