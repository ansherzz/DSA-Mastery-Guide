import java.util.*;

/*
Problem: Sqrt(x)
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a non-negative integer x, return the integer part of its square root.
    The answer should be truncated toward zero.

    Binary search works because if mid^2 is too small, all smaller values are also too small.

Why this pattern fits:
This is a pure monotonic numeric search problem.
    The target is not in an array, but the answer space 0..x is ordered and searchable.

Approach:
Binary search the answer in the range [0, x].
    Use long for multiplication to avoid overflow.
    Keep the greatest mid whose square is <= x.

Worked Example:
Example:
    x = 8
    The integer square root is 2.

Time Complexity: O(log x)
Space Complexity: O(1)
*/

public class P03_SqrtXBinarySearch {
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1;
        int right = x;
        int answer = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void runDemo(int x) {
        System.out.println("x      : " + x);
        System.out.println("sqrt   : " + mySqrt(x));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(4);
        runDemo(8);
        runDemo(2147395599);
    }
}
